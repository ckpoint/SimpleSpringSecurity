package hsim.simple.security.sample;

import hsim.simple.security.service.SimpleSecurityService;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SampleSecurityService extends SimpleSecurityService {

    @Override
    public UserDetails loadUserByUsername(String userName) {

        if (userName.equals("hsim")) {
            SampleUserDetail sampleUserDetail = new SampleUserDetail();
            sampleUserDetail.setLoginId("hsim");
            sampleUserDetail.setPassword("$2a$10$CIKxLPcHjB5w7gqSCcbC8OqMTwpm81cJYIVI7cqKYSw867hBT2FBm");
            sampleUserDetail.setRole("USER");
            return sampleUserDetail;
        }
        return null;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }



}
