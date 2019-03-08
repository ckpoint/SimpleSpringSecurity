package hsim.simple.security.sample;

import hsim.simple.security.account.SimpleUserDetails;
import lombok.Data;

import java.util.List;

@Data
public class SampleUserDetail extends SimpleUserDetails {

    private String loginId;
    private String password;
    private String role;

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
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.loginId;
    }

    @Override
    protected String getRole() {
        return this.role;
    }

    @Override
    protected List<String> getRoles() {
        return null;
    }
}
