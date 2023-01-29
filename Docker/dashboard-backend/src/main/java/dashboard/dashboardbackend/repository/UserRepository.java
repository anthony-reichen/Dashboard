package dashboard.dashboardbackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import dashboard.dashboardbackend.models.User;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAll();    
    User findUserById(String userId);
    User findUserByEmail(String userEmail);
}