package hsim.simple.security.sample;

import hsim.simple.security.service.SimpleSecurityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SampleSecurityServiceTest {

    @Autowired
    private SimpleSecurityService simpleSecurityService;


    @Test
    public void loginTest() {
        this.simpleSecurityService.login("hsim", "$2a$10$CIKxLPcHjB5w7gqSCcbC8OqMTwpm81cJYIVI7cqKYSw867hBT2FBm", SampleUserDetail.class, null);
    }

}