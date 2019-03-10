package hsim.simple.security.account;

import hsim.simple.security.util.ObjectGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class SimpleUserDetails implements UserDetails {

    private static String DEFAULT_NONE_ROLE = "NONE";

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

    @Override
    public abstract String getPassword();

    @Override
    public abstract String getUsername();

    protected abstract String getRole();

    protected abstract List<String> getRoles();
}