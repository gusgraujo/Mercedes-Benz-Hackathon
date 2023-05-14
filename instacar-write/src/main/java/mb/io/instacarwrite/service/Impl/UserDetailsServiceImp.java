package mb.io.instacarwrite.service.Impl;

import mb.io.instacarwrite.model.User;
import mb.io.instacarwrite.repository.UserRepository;
import mb.io.instacarwrite.security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImp implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = this.userRepository.findByEmail(email);
        if(u == null) {
            throw new UsernameNotFoundException(email);
        }
        return new UserSecurity(u.getId(), u.getEmail(), u.getPassword());
    }

}