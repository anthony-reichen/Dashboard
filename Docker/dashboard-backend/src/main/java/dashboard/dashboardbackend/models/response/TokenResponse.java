package dashboard.dashboardbackend.models.response;

public class TokenResponse extends StandardResponse {
    public String token;

    public TokenResponse(String token) {
        super(true, "Authentication successfull!");
        this.token = token;
    }
}