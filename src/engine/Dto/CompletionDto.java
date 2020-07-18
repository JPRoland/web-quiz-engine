package engine.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class CompletionDto {
    @JsonProperty
    private long id;
    @JsonProperty
    private LocalDateTime completedAt;

    public CompletionDto() {}

    public CompletionDto(long id, LocalDateTime completedAt) {
        this.id = id;
        this.completedAt = completedAt;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public void setId(long id) {
        this.id = id;
    }
}
