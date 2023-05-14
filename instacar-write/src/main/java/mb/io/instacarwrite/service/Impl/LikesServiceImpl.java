package mb.io.instacarwrite.service.Impl;


import mb.io.instacarwrite.model.Likes;
import mb.io.instacarwrite.model.Post;
import mb.io.instacarwrite.model.User;
import mb.io.instacarwrite.repository.LikesRepository;
import mb.io.instacarwrite.repository.PostRepository;
import mb.io.instacarwrite.repository.UserRepository;
import mb.io.instacarwrite.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LikesServiceImpl implements LikesService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private LikesRepository likesRepository;

    @Override
    public Likes sendLikes(Long user_id, Long post_id) {

        Optional<User> userLikeSrc = userRepository.findById(user_id);

        //Make a validation query to see if the post was liked by this person
        if (userLikeSrc.isPresent()){
            Optional<Post> postLiked = postRepository.findById(post_id);
            if (postLiked.isPresent()){
                Likes newLike = new Likes(userLikeSrc.get(),postLiked.get(), LocalDateTime.now());
                List<Likes> likePostList = postLiked.get().getLikes();
                likePostList.add(newLike);
                postLiked.get().setLikes(likePostList);
                postRepository.save(postLiked.get());
                likesRepository.save(newLike);
                return newLike;
            }
        }

        return null;
    }
}
