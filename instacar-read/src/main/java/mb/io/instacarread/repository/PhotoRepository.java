package mb.io.instacarread.repository;


import mb.io.instacarread.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    // Add any additional query methods here as needed
}
