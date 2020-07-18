package engine.Dto;

import engine.model.Quiz;

public class DtoTransformer {
    public static Quiz buildQuizFromDto(QuizDto quizDto) {
        Quiz quiz = new Quiz();
        quiz.setId(quizDto.getId());
        quiz.setTitle(quizDto.getTitle());
        quiz.setText(quizDto.getText());
        quiz.setOptions(quizDto.getOptions());
        quiz.setAnswer(quizDto.getAnswer());

        return quiz;
    }

    public static QuizDto buildQuizDtoFromQuiz(Quiz quiz) {
        QuizDto quizDto = new QuizDto();
        quizDto.setId(quiz.getId());
        quizDto.setTitle(quiz.getTitle());
        quizDto.setText(quiz.getText());
        quizDto.setOptions(quiz.getOptions());
        quizDto.setAnswer(quiz.getAnswer());

        return quizDto;
    }
}
