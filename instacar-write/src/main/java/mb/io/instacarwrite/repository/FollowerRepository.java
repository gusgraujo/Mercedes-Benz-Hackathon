package mb.io.instacarwrite.repository;

import mb.io.instacarwrite.model.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {
    // Add any additional query methods here as needed
}
