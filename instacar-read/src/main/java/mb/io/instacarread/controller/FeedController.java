package mb.io.instacarread.controller;

import mb.io.instacarread.model.FollowerFeed;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
public class FeedController {

    @GetMapping("/")
    public ResponseEntity<FollowerFeed> getFeed(@PathVariable Long user_id){
        return null;
    }


}
