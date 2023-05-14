package mb.io.instacarwrite.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UserSecurity implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long   id;
    private String email;
    private String password;

    private  Collection<? extends GrantedAuthority> authorities;

    public UserSecurity(Long id, String email, String password) {
        super();
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
