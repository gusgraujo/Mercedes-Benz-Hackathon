package mb.io.instacarread.model;

import com.sun.istack.NotNull;
import mb.io.instacarread.model.entity.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_user",schema = "public")
public class User extends BaseEntity {

    @NotNull
    @Column(name = "username")
    private String username;
    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "email")
    private String email;

    //Create a list of users ID that the user follows
    Set<Long> follow_list = new HashSet<>();

    //Create a list of users ID that follow the user
    Set<Long> follower_list = new HashSet<>();


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Long> getFollow_list() {
        return follow_list;
    }

    public void setFollow_list(Set<Long> follow_list) {
        this.follow_list = follow_list;
    }

    public Set<Long> getFollower_list() {
        return follower_list;
    }

    public void setFollower_list(Set<Long> follower_list) {
        this.follower_list = follower_list;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}