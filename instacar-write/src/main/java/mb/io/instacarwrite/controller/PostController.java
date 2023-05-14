package mb.io.instacarwrite.controller;

import mb.io.instacarwrite.model.Post;
import mb.io.instacarwrite.model.request.PostRequest;
import mb.io.instacarwrite.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest){
        Post post = postService.createPost(postRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> findById(@PathVariable Long postId){
        Post post = postService.findById(postId);
        return ResponseEntity.ok(post);
    }

    @PostMapping("/{postId}/uploadImage")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable Long postId){
        postService.addPhotoInPost(file,postId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{postId}/publish")
    public ResponseEntity<?> publish(@PathVariable Long postId){
       Post post = postService.publishPost(postId);
       return ResponseEntity.ok(post);
    }

    @PatchMapping("{postId}/archive")
    public ResponseEntity<?> archive(@PathVariable Long postId){
        postService.archivePost(postId);
        return ResponseEntity.noContent().build();
    }


}
