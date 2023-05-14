package mb.io.instacarwrite.service;

import mb.io.instacarwrite.model.Post;
import mb.io.instacarwrite.model.request.PostRequest;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {

    Post createPost(PostRequest postRequest);

    void addPhotoInPost(MultipartFile file, Long postId);

    Post findById(Long postId);

    Post publishPost(Long postId);

    void archivePost(long postId);
}
