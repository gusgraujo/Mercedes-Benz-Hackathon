package mb.io.instacarwrite.repository;


import mb.io.instacarwrite.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    // Add any additional query methods here as needed
}
