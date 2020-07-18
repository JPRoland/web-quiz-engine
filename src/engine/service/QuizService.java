package engine.service;

import engine.exceptions.QuizNotFoundException;
import engine.model.*;
import engine.repository.CompletionRepository;
import engine.repository.QuizRepository;
import engine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Component
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompletionRepository completionRepository;

    public QuizService() {}

    public Quiz get(long id) {
        return quizRepository.findById(id).orElseThrow(QuizNotFoundException::new);
    }

    public Page<Quiz> getAll(int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, 10);
        return quizRepository.findAll(pageable);
    }

    public void delete(long id, String userEmail) {
        Quiz quiz = get(id);
        if (!userEmail.equals(quiz.getAuthor().getEmail())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
        }
        quizRepository.delete(quiz);
    }

    public Quiz save(Quiz quiz, String authorEmail) {
        User author = userRepository.findByEmail(authorEmail);
        if (author == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User not found");
        }
        quiz.setAuthor(author);
        return quizRepository.save(quiz);
    }

    public Optional<QuizResponse> checkAnswer(long id, QuizAnswer answer, String userEmail) {
        QuizResponse qr = new QuizResponse();
        Quiz quiz = get(id);
        Integer[] provided = answer.getAnswer();
        Integer[] actual = quiz.getAnswer();
        actual = actual == null ? new Integer[0] : actual;

        Arrays.sort(provided);
        Arrays.sort(actual);

        if (provided.length == actual.length && Arrays.equals(provided, actual)) {
            User user = userRepository.findByEmail(userEmail);
            if (user == null) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User not found");
            }
            qr.setSuccess(true);
            qr.setFeedback("Correct! You got the right answer!");
            completionRepository.save(new Completion(quiz, user));
        } else {
            qr.setSuccess(false);
            qr.setFeedback("Sorry, wrong answer. Please try again.");
        }

        return Optional.ofNullable(qr);
    }
}
