package mb.io.instacarread.model;

import mb.io.instacarread.model.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name="photo",schema="public")
public class Photo extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    @Column(name = "uri")
    private String uri;


    public Photo() {}

    public Photo(Post post, String uri) {
        this.post = post;
        this.uri = uri;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
