package dashboard.dashboardbackend.models.response;

import java.util.List;

import dashboard.dashboardbackend.models.User;
import dashboard.dashboardbackend.models.widgets.Widget;

public class UserResponse extends StandardResponse {
    public String id;
    public String firstName;
    public String lastName;
    public String email;
    public List<Widget> widgets;

    public UserResponse(User user, String message) {
        super(true, message);
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.id = user.getId();
        this.widgets = user.getWidgets();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
