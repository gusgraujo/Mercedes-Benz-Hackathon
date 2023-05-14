package mb.io.instacarread.service.Impl;

import mb.io.instacarread.model.FollowerFeed;
import mb.io.instacarread.model.User;
import mb.io.instacarread.repository.UserRepository;
import mb.io.instacarread.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


public class FeedServiceImpl implements FeedService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<FollowerFeed> generateFeed(@Validated User user, Pageable pageable) {
        Optional<User> userRequested = userRepository.findById(user.getId());


        if (userRequested.isPresent()) {
            Set<Long> userFollowList = user.getFollow_list();
            return null;
        } else {
            return Page.empty();
        }
    }
}