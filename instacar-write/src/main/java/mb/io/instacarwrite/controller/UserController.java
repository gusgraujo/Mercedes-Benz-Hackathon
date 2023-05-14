package mb.io.instacarwrite.controller;

import mb.io.instacarwrite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/follow")
    public ResponseEntity<?> followUser(){
        return  ResponseEntity.ok().build();
    }

    @PostMapping("/unfollow")
    public ResponseEntity<?> unfollowUser(){
        return  ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/posts")
    public ResponseEntity<?> getPostsOfUser(@PathVariable Long userId){
        return  ResponseEntity.ok().build();
    }
}
