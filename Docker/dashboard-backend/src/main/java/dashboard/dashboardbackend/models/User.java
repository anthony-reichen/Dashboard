package dashboard.dashboardbackend.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import dashboard.dashboardbackend.models.widgets.Widget;

@Document
public class User{
    @Id
    private String id;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @Indexed(unique = true)
    @Email
    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    private String password;

    private String jwt;
    private String spotifyRefreshToken;
    private String spotifyAccessToken;
    private List<Widget> widgets = new ArrayList<Widget>();

    public User() {
    }

    public String getId() {
        return id;
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

    public String getPassword() {
        return password;
    }

    public String getSpotifyAccessToken() {
        return spotifyAccessToken;
    }

    public String getSpotifyRefreshToken() {
        return spotifyRefreshToken;
    }

    public String getJwt() {
        return jwt;
    }
    
    public List<Widget> getWidgets() {
		return widgets;
	}
    

	public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSpotifyAccessToken(String spotifyAccessToken) {
        this.spotifyAccessToken = spotifyAccessToken;
    }

    public void setSpotifyRefreshToken(String spotifyRefreshToken) {
        this.spotifyRefreshToken = spotifyRefreshToken;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
    
    public void removeWidget(Widget w) {
    	this.widgets.remove(w);
    }
    
    public void addWidget(Widget w) {
    	this.widgets.add(w);
    }
    
}
