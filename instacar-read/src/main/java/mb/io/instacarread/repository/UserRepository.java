package mb.io.instacarread.repository;

import mb.io.instacarread.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT t FROM User t where t.username = :username")
    public User findByUsername(@Param("username") String username);
}
