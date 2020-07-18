package engine.model;

public class QuizResponse {
    private boolean success;
    private String feedback;

    public String getFeedback() {
        return feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
