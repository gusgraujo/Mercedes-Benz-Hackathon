package mb.io.instacarwrite.service;

import mb.io.instacarwrite.model.Follower;

public interface FollowerService {

    Follower followUser(Long user_id, Long follower_id);
}
