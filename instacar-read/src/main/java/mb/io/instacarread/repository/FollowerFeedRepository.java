package mb.io.instacarread.repository;


import mb.io.instacarread.model.FollowerFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerFeedRepository extends JpaRepository<FollowerFeed, Long> {
}
