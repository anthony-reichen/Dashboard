package dashboard.dashboardbackend.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.time.Instant;

import dashboard.dashboardbackend.models.User;
import dashboard.dashboardbackend.models.response.TokenResponse;
import dashboard.dashboardbackend.repository.UserRepository;

@Service
public class AuthenticationManager {

    @Autowired
    private UserRepository userRepository;

    static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * The authenticateUser function takes in an email and a raw password,
     * finds the user with that email, and checks if the password matches.
     * If it does match, then we return a TokenResponse object containing a JWT.
     * Otherwise we throw an exception.
     *
     * @param email  Find the user in the database
     * @param String Pass the email of the user that is trying to authenticate
     *
     * @return A tokenresponse object
     */
    public TokenResponse authenticateUser(String email, String rawPassword) throws RuntimeException {
        try {
            User foundUser = userRepository.findUserByEmail(email);
            if (new BCryptPasswordEncoder().matches(rawPassword, foundUser.getPassword())) {
                return new TokenResponse(Jwts.builder()
                        .claim("firstName", foundUser.getFirstName())
                        .claim("lastName", foundUser.getLastName())
                        .claim("email", foundUser.getEmail())
                        .setSubject(foundUser.getEmail())
                        .setId(foundUser.getId())
                        .setIssuedAt(Date.from(Instant.now()))
                        .setExpiration(Date.from(Instant.now().plus(5l, ChronoUnit.MINUTES)))
                        .signWith(key)
                        .compact());
            } else
                throw new RuntimeException("Email or password don't match");
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
