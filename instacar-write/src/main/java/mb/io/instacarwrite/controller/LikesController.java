package mb.io.instacarwrite.controller;

import mb.io.instacarwrite.model.Likes;
import mb.io.instacarwrite.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/likes")
public class LikesController {

    @Autowired
    private LikesService likesService;

    @PostMapping("/send")
    public ResponseEntity<Likes> sendLike(@PathVariable Long post_id, @PathVariable Long user_id){
        Likes newLike = likesService.sendLikes(user_id,post_id);

        if (newLike != null){
            return new ResponseEntity<>(newLike, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
