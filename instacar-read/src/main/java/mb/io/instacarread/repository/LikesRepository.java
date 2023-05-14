package mb.io.instacarread.repository;


import mb.io.instacarread.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
}
