package mb.io.instacarread.repository;

import mb.io.instacarread.model.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {
    // Add any additional query methods here as needed
}
