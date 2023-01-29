package dashboard.dashboardbackend.models.response;

public abstract class StandardResponse {
    public boolean success;
    public String message;

    public StandardResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
