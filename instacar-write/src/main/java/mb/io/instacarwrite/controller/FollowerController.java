package mb.io.instacarwrite.controller;

import mb.io.instacarwrite.model.Follower;
import mb.io.instacarwrite.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/follow")
public class FollowerController {

    @Autowired
    private FollowerService followerService;

    @PostMapping("/")
    public ResponseEntity<Follower> followUser(@PathVariable Long user_id, @PathVariable Long follower_id){
        return null;
    }

}
