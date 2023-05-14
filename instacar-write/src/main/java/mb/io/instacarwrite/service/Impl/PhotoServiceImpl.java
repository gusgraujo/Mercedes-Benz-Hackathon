package mb.io.instacarwrite.service.Impl;

import mb.io.instacarwrite.model.Photo;
import mb.io.instacarwrite.model.Post;
import mb.io.instacarwrite.repository.PhotoRepository;
import mb.io.instacarwrite.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public Photo savePhoto(Post post, String photoUrl){
        Photo photo = new Photo();
        photo.setGuuid(UUID.randomUUID());
        photo.setCreatedAt(LocalDateTime.now());
        photo.setPost(post);
        photo.setUri(photoUrl);
        photo = photoRepository.save(photo);
        return photo;
    }
}
