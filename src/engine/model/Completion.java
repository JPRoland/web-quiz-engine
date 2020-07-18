package engine.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Completion {
    @Id
    @GeneratedValue
    private long id;

    @CreationTimestamp
    private LocalDateTime completedAt;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Completion() {}

    public Completion(Quiz quiz, User user) {
        this.quiz = quiz;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public User getUser() {
        return user;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
