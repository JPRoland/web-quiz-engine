package engine.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class QuizDto {
    QuizDto() {}

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;

    @JsonProperty
    @NotBlank(message = "Quiz title is required")
    private String title;

    @JsonProperty
    @NotBlank(message = "Quiz text is required")
    private String text;

    @JsonProperty
    @NotNull
    @Size(min = 2, message = "Quiz options should contain at least 2 items")
    private String[] options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer[] answer;

    public long getId() {
        return id;
    }

    public Integer[] getAnswer() {
        return answer;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAnswer(Integer[] answer) {
        this.answer = answer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}