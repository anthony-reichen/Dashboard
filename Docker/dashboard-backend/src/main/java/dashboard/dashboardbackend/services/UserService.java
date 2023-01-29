package dashboard.dashboardbackend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.lang.NullPointerException;

import dashboard.dashboardbackend.models.User;
import dashboard.dashboardbackend.models.response.UserResponse;
import dashboard.dashboardbackend.models.widgets.WeatherWidget;
import dashboard.dashboardbackend.models.widgets.Widget;
import dashboard.dashboardbackend.repository.UserRepository;
import dashboard.dashboardbackend.utils.JwtUtils;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * The createUser function creates a new user in the database.
     * 
     * @param newUser Pass in the user object that is being created
     * @return A user object
     */
    public UserResponse createUser(User newUser) throws RuntimeException {
        try {
            newUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));
            UserResponse savedUser = new UserResponse(userRepository.save(newUser),
                    "Created User " + newUser.getEmail());
            return savedUser;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    /**
     * The getAllUsers function returns a list of all users in the database.
     * 
     * @return A list of userResponse objects, which are created by the constructor
     */
    public List<UserResponse> getAllUsers() throws RuntimeException {
        List<UserResponse> userResponses = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            try {
                userResponses.add(new UserResponse(user, "Got user " + user.getEmail()));
            } catch (RuntimeException e) {
                throw e;
            }
        }
        return userResponses;
    }

    /**
     * The getUserByEmail function takes in a String email and returns the User
     * object
     * that has the same email.
     *
     * @param email Find the user with that email
     *
     * @return A userResponse object
     */
    public UserResponse getUserByEmail(String email) throws RuntimeException {
        try {
            UserResponse response = new UserResponse(userRepository.findUserByEmail(email), "Got User " + email);
            return response;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    /**
     * The getUserWidgets function returns a list of widgets that belong to the
     * user.
     * 
     * @param jwt Get the email of the user who is currently logged in
     *
     * @return A list of widgets that belong to a specific user
     */
    public List<Widget> getUserWidgets(String jwt) throws RuntimeException {
        String userEmail = JwtUtils.getEmail(jwt);
        try {
            User foundUser = userRepository.findUserByEmail(userEmail);
            return foundUser.getWidgets();
        } catch (RuntimeException e) {
            throw e;
        }
    }

    /**
     * The getUserById function takes in a String id and returns the User object
     * with that id.
     * 
     * @param id Identify the user that we want to retrieve
     *
     * @return A userResponse object
     */
    public UserResponse getUserById(String id) throws RuntimeException {
        try {
            return new UserResponse(userRepository.findUserById(id), "Got User " + id);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    /**
     * The updateUserById function updates a user's information in the database.
     * 
     * @param id      Find the user to update
     * @param newData Pass in the new data to be updated
     *
     * @return A userResponse object
     */
    public UserResponse updateUserById(String id, User newData) throws RuntimeException {
        if (id.equals(newData.getId())) {
            try {
                User foundUser = userRepository.findUserById(id);
                foundUser = newData;
                userRepository.save(foundUser);
                return new UserResponse(foundUser,
                        "User with ID " + foundUser.getId() + " has been successfully updated!");
            } catch (RuntimeException e) {
                throw e;
            }
        } else {
            throw new RuntimeException("Id param is not equal to new data Id");
        }
    }

    /**
     * The updateUserByEmail function updates a user's information by their email.
     *
     * @param email   Find the user by email
     * @param newData Pass the user object that will be updated
     *
     * @return A userResponse object
     */
    public UserResponse updateUserByEmail(String email, User newData) throws RuntimeException {
        if (email.equals(newData.getEmail())) {
            try {
                User foundUser = userRepository.findUserByEmail(email);
                foundUser = newData;
                userRepository.save(foundUser);
                return new UserResponse(foundUser,
                        "User with email " + foundUser.getEmail() + " has been successfully updated!");
            } catch (RuntimeException e) {
                throw e;
            }
        } else {
            throw new RuntimeException("Email param is not equal to new data Email");
        }
    }

    /**
     * The deleteUserById function deletes a user from the database by their ID.
     * 
     * @param id Find the user with the given id
     *
     * @return A userResponse object
     */
    public UserResponse deleteUserById(String id) throws RuntimeException {
        try {
            User foundUser = userRepository.findUserById(id);
            userRepository.delete(foundUser);
            return new UserResponse(foundUser, "User " + foundUser.getEmail() + " has been successfully deleted!");
        } catch (RuntimeException e) {
            throw e;
        }
    }

    /**
     * The deleteUserByEmail function takes in a String email and deletes the user
     * with that email from the database.
     * 
     * @param email Find the user in the database
     *
     * @return A userResponse object
     */
    public UserResponse deleteUserByEmail(String email) throws RuntimeException {
        try {
            User foundUser = userRepository.findUserByEmail(email);
            userRepository.delete(foundUser);
            return new UserResponse(foundUser, "User " + foundUser.getEmail() + " has been successfully deleted!");
        } catch (RuntimeException e) {
            throw e;
        }
    }

    /**
     * The addWeatherWidget function adds a new weather widget to the user's
     * dashboard.
     * 
     * @param insee Get the weather data from the api
     * @param jwt   Used to get the user email
     *
     * @return The weatherwidget object that was created
     */
    public WeatherWidget addWeatherWidget(String insee, String jwt) throws RuntimeException {
        String userEmail = JwtUtils.getEmail(jwt);
        try {
            User user = userRepository.findUserByEmail(userEmail);
            WeatherWidget addW = new WeatherWidget(insee);
            user.addWidget(addW);
            userRepository.save(user);
            return addW;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    /**
     * The editWeatherWidget function is used to edit the insee code of a weather
     * widget.
     * 
     * @param insee    Set the value of the insee field of a weatherwidget object
     * @param widgetId Used to modify the right widget
     * @param jwt      Used to recover user email
     *
     * @return A weatherwidget object
     */
    public WeatherWidget editWeatherWidget(String insee, String widgetId, String jwt) throws RuntimeException {
        String userEmail = JwtUtils.getEmail(jwt);
        try {
            User user = userRepository.findUserByEmail(userEmail);
            WeatherWidget foundWidget = null;
            for (Widget element : user.getWidgets()) {
                System.out.println("elem ID: " + element.getId() + " --- search ID: " + widgetId);
                if (element.getId().equals(widgetId)) {
                    foundWidget = (WeatherWidget) element;
                    System.out.println("found");
                    break;
                }
            }
            System.out.println("insee: " + insee);
            System.out.println(foundWidget);
            foundWidget.setInsee(insee);
            userRepository.save(user);
            return (WeatherWidget) foundWidget;
        } catch (

        RuntimeException e) {
            throw e;
        }
    }
}