package mb.io.instacarwrite.service.Impl;

import mb.io.instacarwrite.model.User;
import mb.io.instacarwrite.repository.UserRepository;
import mb.io.instacarwrite.service.UserService;
import mb.io.instacarwrite.service.exception.NoElementException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User newUser) {
        try {
            if (newUser.getGuuid() == null) {
                newUser.setGuuid(UUID.randomUUID());
            }
            if (newUser.getCreatedAt() == null) {
                newUser.setCreatedAt(LocalDateTime.now());
            }
            newUser.setPassword(encoder.encode(newUser.getPassword()));
            newUser.setUpdatedAt(LocalDateTime.now());
            return userRepository.save(newUser);
        } catch (Exception e) {
            throw new RuntimeException("Error saving user", e);
        }
    }


    @Override
    public User findById(Long id)  {
        Optional<User> optionalEntity = userRepository.findById(id);
        if (optionalEntity.isPresent()) {
            return optionalEntity.get();
        } else {
            throw new NoElementException(String.format("User with id %d not found!", id));
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User user) {
        Optional<User> optionalEntity = userRepository.findById(id);
        if (optionalEntity.isPresent()) {
            User existingUser = optionalEntity.get();
            existingUser.setUsername(user.getUsername());
            existingUser.setUpdatedAt(LocalDateTime.now());
            return saveUser(existingUser);
        } else {
            throw new NoElementException(String.format("User with id %d not found!", id));
        }

    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
