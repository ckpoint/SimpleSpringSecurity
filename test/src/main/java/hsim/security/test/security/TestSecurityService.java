package hsim.security.test.security;

import hsim.security.test.account.service.AccountService;
import hsim.simple.security.account.SimpleUserDetails;
import hsim.simple.security.service.SimpleSecurityService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestSecurityService extends SimpleSecurityService {

    @NonNull
    private final AccountService accountService;

    @Override
    public SimpleUserDetails loadUserByUsername(String userName) {
        return new TestUserDetail().updateFromObj(accountService.findAccountFromUserName(userName));
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/public/**").permitAll()
                .anyRequest().authenticated();
    }

}
