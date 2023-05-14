package mb.io.instacarwrite.model;

import javax.persistence.*;

import mb.io.instacarwrite.model.entity.BaseEntity;

@Entity
@Table(name="deep_link",schema = "public")
public class DeepLink extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    @Column(name = "uri")
    private String uri;

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

    public DeepLink() {    }

    public DeepLink(Post post, String uri) {
        this.post = post;
        this.uri = uri;
    }
}
