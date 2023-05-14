package mb.io.instacarwrite.service;


import mb.io.instacarwrite.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DbService {

    @Autowired
    private UserService userService;

    public void instantiateTestDatabase(){
        seedUsers();

    }

    private void seedUsers() {
        for (int i = 0; i <10 ; i++) {
            User user = new User();
            user.setEmail("user"+i+"test@gmail.com");
            user.setUsername("test"+i+"user");
            user.setPassword("!!123456Asd");
            userService.saveUser(user);
        }
    }


}
