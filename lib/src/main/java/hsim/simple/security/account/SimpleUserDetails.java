package hsim.simple.security.account;

import hsim.simple.security.util.ObjectGenerator;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Simple user details.
 */
@Data
public class SimpleUserDetails implements UserDetails {

    private static String DEFAULT_NONE_ROLE = "NONE";

    private String username;
    private String password;
    private String role;
    private List<String> roles;

    /**
     * Update from obj simple user details.
     *
     * @param object the object
     * @return the simple user details
     */
    public SimpleUserDetails updateFromObj(Object object) {
        ObjectGenerator.enableFieldModelMapper().map(object, this);
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.getRoles() != null && !this.getRoles().isEmpty()) {
            return this.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        } else if (!StringUtils.isEmpty(this.getRole())) {
            return Collections.singletonList(new SimpleGrantedAuthority(this.getRole()));
        } else {
            return Collections.singletonList(new SimpleGrantedAuthority(DEFAULT_NONE_ROLE));
        }
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

    public void addRole(String role) {
        if (this.roles == null) {
            this.roles = new ArrayList<>();
        }
        this.roles.add(role);
    }
}