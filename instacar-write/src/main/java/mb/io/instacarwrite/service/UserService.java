package mb.io.instacarwrite.service;

import mb.io.instacarwrite.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User entity);

    User findById(Long id) ;

    List<User> getAllUsers();

    User updateUser(Long id, User user);

    void deleteUserById(Long id);

    User findByUsername(String username);

}