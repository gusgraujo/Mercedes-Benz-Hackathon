package mb.io.instacarwrite.service.Impl;

import mb.io.instacarwrite.model.Follower;

import mb.io.instacarwrite.model.User;
import mb.io.instacarwrite.repository.FollowerRepository;
import mb.io.instacarwrite.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class FollowerServiceImpl implements FollowerService {

    @Autowired
    private FollowerRepository followerRepository;

    @Override
    public Follower followUser(Long user_id, Long follower_id) {

        return null;
    }
}
