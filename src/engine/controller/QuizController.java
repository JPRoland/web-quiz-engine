package engine.controller;

import engine.Dto.CompletionDto;
import engine.Dto.DtoTransformer;
import engine.Dto.QuizDto;
import engine.model.Quiz;
import engine.model.QuizAnswer;
import engine.model.QuizResponse;
import engine.service.CompletionService;
import engine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/quizzes", produces = "application/json")
public class QuizController {
    @Autowired
    QuizService quizService;

    @Autowired
    CompletionService completionService;

    @GetMapping
    public Page<QuizDto> getAllQuizzes(@RequestParam(name = "page", defaultValue = "0") int pageNum) {
        return quizService.getAll(pageNum).map(DtoTransformer::buildQuizDtoFromQuiz);
    }

    @GetMapping("/{id}")
    public QuizDto getQuizById(@PathVariable long id) {
        Quiz quiz = quizService.get(id);

        return DtoTransformer.buildQuizDtoFromQuiz(quiz);
    }

    @GetMapping("/completed")
    public Page<CompletionDto> getCompletions(
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @Autowired Principal principal
    ) {
        return completionService.getPage(pageNumber, principal.getName())
                .map(completion ->
                        new CompletionDto(completion.getQuiz().getId(), completion.getCompletedAt())
                );
    }

    @PostMapping
    @ResponseBody
    public QuizDto addQuiz(@RequestBody @Valid @NotNull Quiz quiz, Principal principal) {
        quiz = quizService.save(quiz, principal.getName());
        return DtoTransformer.buildQuizDtoFromQuiz(quiz);
    }

    @PostMapping("/{id}/solve")
    public ResponseEntity<QuizResponse> solveQuiz(@PathVariable long id, @RequestBody @NotNull QuizAnswer answer, Principal principal) {
        Optional<QuizResponse> quizResponse = quizService.checkAnswer(id, answer, principal.getName());

        return quizResponse.map(q -> ResponseEntity.ok().body(q))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuiz(@PathVariable long id, Principal principal) {
        quizService.delete(id, principal.getName());
    }
}
