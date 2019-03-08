package hsim.simple.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
public class PasswordEncoderTest {

    @Test
    public void passwordMatchTest() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String a = passwordEncoder.encode("xodhs0314");
        String b = passwordEncoder.encode("xodhs0314");

        log.info(a);
        log.info(b);
        log.info("match : " + passwordEncoder.matches(a, b));

        Assert.assertTrue(passwordEncoder.matches(a, b));
    }
}
