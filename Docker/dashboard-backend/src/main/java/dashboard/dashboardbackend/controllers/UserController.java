package dashboard.dashboardbackend.controllers;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import dashboard.dashboardbackend.services.UserService;
import dashboard.dashboardbackend.managers.AuthenticationManager;
import dashboard.dashboardbackend.models.User;
import dashboard.dashboardbackend.models.response.ErrorResponse;
import dashboard.dashboardbackend.models.response.StandardResponse;
import dashboard.dashboardbackend.models.response.TokenResponse;
import dashboard.dashboardbackend.models.response.UserResponse;
import dashboard.dashboardbackend.models.widgets.Widget;
import dashboard.dashboardbackend.repository.UserRepository;

@RestController
@CrossOrigin(origins = "http://docker-apache-1:80")
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    private String format = "LOG => %s";

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * The login function is used to authenticate a user.
     * 
     * @param Map&lt;String Pass the email and password to the login function
     * @param String&gt;    Specify the type of the return value
     *
     * @return A tokenresponse
     */
    @PostMapping(value = "/login")
    public ResponseEntity<? extends StandardResponse> login(@RequestBody Map<String, String> credentials) {
        try {
            logger.info(String.format(format, credentials.get("email") + " is trying to login."));
            return new ResponseEntity<TokenResponse>(
                    authenticationManager.authenticateUser(credentials.get("email"), credentials.get("password")),
                    HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse("Email or password don't match"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * The register function is used to register a new user.
     * 
     * @param User Pass the user object to the register method
     *
     * @return The user object that was created
     */
    @PostMapping(value = "/")
    public ResponseEntity<? extends StandardResponse> register(@RequestBody @Valid User user) {
        logger.info(String.format(format, user.getEmail() + " called Register."));
        try {
            return new ResponseEntity<UserResponse>(userService.createUser(user), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * The getAllUsers function retrieves all users from the database.
     *
     * @return A list of user objects.
     */
    @GetMapping(value = "/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        logger.info(String.format(format, "Getting all users."));
        return new ResponseEntity<List<UserResponse>>(userService.getAllUsers(), HttpStatus.OK);
    }

    /**
     * The geutUserById function retrieves a user by its email.
     *
     * @return A user object.
     */
    @GetMapping(value = "/email/{email}")
    public ResponseEntity<? extends StandardResponse> getUserByEmail(@PathVariable String email) {
        logger.info(String.format(format, "Getting user with email: " + email));
        try {
            return new ResponseEntity<UserResponse>(userService.getUserByEmail(email), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse("Couldn't get user"), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * The getUserById function returns a UserResponse object containing the user
     * with the given id.
     * 
     * @param String Pass in the id of the user that is being requested
     *
     * @return A userresponse object
     */
    @GetMapping(value = "/id/{id}")
    public ResponseEntity<? extends StandardResponse> getUserById(@PathVariable String id) {
        logger.info(String.format(format, "Getting user with id: " + id));
        try {
            return new ResponseEntity<UserResponse>(userService.getUserById(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse("Couldn't get user"), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * The updateUserById function updates a user's information by their id.
     * 
     * @param String       Identify the user
     * @param @RequestBody Specify the type of data that is being sent to the server
     *
     * @return A userresponse object
     */
    @PatchMapping(value = "/id/{id}")
    public ResponseEntity<? extends StandardResponse> updateUserById(@PathVariable String id, @RequestBody User data) {
        logger.info(String.format(format, id + " called Update."));
        try {
            return new ResponseEntity<UserResponse>(userService.updateUserById(id, data), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse("Couldn't update user " + id),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * The updateUserByEmail function updates a user's information by their email.
     * 
     * @param String       Pass the email of the user to be deleted
     * @param @RequestBody Send the json object to the server
     *
     * @return A responseentity
     */
    @PatchMapping(value = "/email/{email}")
    public ResponseEntity<? extends StandardResponse> updateUserByEmail(@PathVariable String email,
            @RequestBody User data) {
        logger.info(String.format(format, email + " called Update."));
        try {
            return new ResponseEntity<UserResponse>(userService.updateUserByEmail(email, data), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse("Couldn't update user " + email),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * The updateUserByEmail function updates a user's information by their email.
     * 
     * @param String       Pass the email of the user to be deleted
     * @param @RequestBody Send the json object to the server
     *
     * @return A responseentity
     */
    @GetMapping(value = "/getWidgets")
    public ResponseEntity<?> getUserWidget(@RequestHeader("Authorization") String jwt) {
        try {
            return new ResponseEntity<>(userService.getUserWidgets(jwt), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse("Couldn't get widgets"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * The deleteUserById function deletes a user from the database.
     * 
     * @param String Pass the id of the user to be updated
     *
     * @return A responseentity&lt;? extends standardresponse&gt;
     */
    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<? extends StandardResponse> deleteUserById(@PathVariable String id) {
        logger.info(String.format(format, id + " called Delete."));
        try {
            return new ResponseEntity<UserResponse>(userService.deleteUserById(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse("Couldn't delete user"), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * The deleteUserByEmail function is used to delete a user by email.
     * 
     * @param String Format the log message
     *
     * @return A responseentity&lt;userresponse&gt;
     */
    @DeleteMapping(value = "/email/{email}")
    public ResponseEntity<? extends StandardResponse> deleteUserByEmail(@PathVariable String email) {
        logger.info(String.format(format, email + " called Delete."));
        try {
            return new ResponseEntity<UserResponse>(userService.deleteUserByEmail(email), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse("Couldn't delete user"), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * The register function is used to register a new widget.
     * 
     * @param widget Pass the user object to the addWidget method
     *
     * @return The widget object that was created
     */
    @PostMapping(value = "/weather/addreport/{insee}")
    public ResponseEntity<?> addWeatherWidget(@PathVariable String insee, @RequestHeader("Authorization") String jwt) {
        logger.info(String.format(format, "WeatherWidget for " + insee + " is being created"));
        try {
            return new ResponseEntity<Widget>(userService.addWeatherWidget(insee, jwt), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value = "/weather/edit/{id}/{insee}")
    public ResponseEntity<?> editWeatherWidget(@PathVariable String id, @PathVariable String insee,
            @RequestHeader("Authorization") String jwt) {
        logger.info(String.format(format, "WeatherWidget for " + insee + " is being edited"));
        try {
            return new ResponseEntity<Widget>(userService.editWeatherWidget(insee, id, jwt), HttpStatus.OK);
        } catch (RuntimeException e) {
            throw e;
        }
    }
}