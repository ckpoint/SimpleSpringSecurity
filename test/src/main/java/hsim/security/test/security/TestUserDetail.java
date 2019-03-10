package hsim.security.test.security;

import hsim.simple.security.account.SimpleUserDetails;
import lombok.Data;

import java.util.List;

@Data
public class TestUserDetail extends SimpleUserDetails {

    private Long id;
    private String userName;
    private String password;
    private String role;

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
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
