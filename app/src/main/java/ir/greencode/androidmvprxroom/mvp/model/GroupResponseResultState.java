package ir.greencode.androidmvprxroom.mvp.model;

public class GroupResponseResultState {
    private boolean success;
    private String message;

    public boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
