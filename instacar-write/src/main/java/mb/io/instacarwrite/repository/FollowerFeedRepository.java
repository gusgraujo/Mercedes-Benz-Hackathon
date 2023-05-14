package mb.io.instacarwrite.repository;


import mb.io.instacarwrite.model.FollowerFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerFeedRepository extends JpaRepository<FollowerFeed, Long> {
}
