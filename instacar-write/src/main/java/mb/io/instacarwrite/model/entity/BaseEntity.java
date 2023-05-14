package mb.io.instacarwrite.model.entity;

import com.sun.istack.NotNull;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long id;
    @NotNull
    @Column(name = "createdAt")
    public LocalDateTime createdAt;
    @NotNull
    @Column(name = "updatedAt")
    public LocalDateTime updatedAt;

    @NotNull
    @Column(name = "guuid")
    public UUID guuid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UUID getGuuid() {
        return guuid;
    }

    public void setGuuid(UUID guuid) {
        this.guuid = guuid;
    }
}
