package hsim.simple.security.account;

import hsim.simple.security.util.ObjectGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class SimpleUserDetails implements UserDetails {

    public SimpleUserDetails updateFromObj(Object object) {
        ObjectGenerator.enableFieldModelMapper().map(object, this);
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.getRoles() != null && !this.getRoles().isEmpty()) {
            return this.getRoles().stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        } else if (!StringUtils.isEmpty(this.getRole())) {
            return Arrays.asList(new SimpleGrantedAuthority(this.getRole()));
        } else {
            return Arrays.asList(new SimpleGrantedAuthority("NONE"));
        }
    }

    @Override
    public abstract boolean isAccountNonExpired();

    @Override
    public abstract boolean isAccountNonLocked();

    @Override
    public abstract boolean isCredentialsNonExpired();

    @Override
    public abstract boolean isEnabled();

    @Override
    public abstract String getPassword();

    @Override
    public abstract String getUsername();

    protected abstract String getRole();

    protected abstract List<String> getRoles();
}