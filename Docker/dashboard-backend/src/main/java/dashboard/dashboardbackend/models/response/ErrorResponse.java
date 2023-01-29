package dashboard.dashboardbackend.models.response;

public class ErrorResponse extends StandardResponse {
    public ErrorResponse(String message) {
        super(false, message);
    }
}
