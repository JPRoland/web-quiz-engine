package engine.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    private String password;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Quiz> userQuizzes;

    public User() {}

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserQuizzes(List<Quiz> userQuizzes) {
        this.userQuizzes = userQuizzes;
    }

    public List<Quiz> getUserQuizzes() {
        return userQuizzes;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
