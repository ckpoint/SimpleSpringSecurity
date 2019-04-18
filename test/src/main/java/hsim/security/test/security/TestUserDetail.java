package hsim.security.test.security;

import hsim.simple.security.account.SimpleUserDetails;
import lombok.Data;

import java.util.List;

@Data
public class TestUserDetail extends SimpleUserDetails {
    private Long id;
}
