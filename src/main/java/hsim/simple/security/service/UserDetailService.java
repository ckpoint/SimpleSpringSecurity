package hsim.simple.security.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    @NonNull
    private SimpleSecurityService simpleSecurityService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return this.simpleSecurityService.loadUserByUsername(userName);
    }
}
