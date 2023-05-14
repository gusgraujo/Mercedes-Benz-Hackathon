package mb.io.instacarread.service;

import mb.io.instacarread.model.FollowerFeed;
import mb.io.instacarread.model.User;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeedService {

    Page<FollowerFeed> generateFeed(User user, Pageable pageable);

}