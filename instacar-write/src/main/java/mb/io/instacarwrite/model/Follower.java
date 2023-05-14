package mb.io.instacarwrite.model;

import mb.io.instacarwrite.model.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "followers",schema = "public")
public class Follower extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private User follower;

    @Column(name = "followed_at")
    private LocalDateTime followedAt;

}
