package mb.io.instacarwrite.service;

import mb.io.instacarwrite.model.Photo;
import mb.io.instacarwrite.model.Post;
import org.springframework.stereotype.Service;


public interface PhotoService {
    public Photo savePhoto(Post post, String photoUrl);

}
