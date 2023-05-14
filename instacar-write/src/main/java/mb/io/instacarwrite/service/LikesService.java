package mb.io.instacarwrite.service;

import mb.io.instacarwrite.model.Likes;

import java.util.Optional;

public interface LikesService {

    Likes sendLikes(Long user_id, Long post_id);
}
