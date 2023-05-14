package mb.io.instacarwrite.repository;

import mb.io.instacarwrite.model.DeepLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeepLinkRepository extends JpaRepository<DeepLink,Long> {
}
