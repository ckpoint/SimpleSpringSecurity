package hsim.security.test.account.controller;

import hsim.security.test.account.model.AccountModel;
import hsim.security.test.account.service.AccountService;
import hsim.security.test.security.TestSecurityService;
import hsim.security.test.security.TestUserDetail;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
public class AccountController {

    @NonNull
    private final AccountService accountService;

    @NonNull
    private final TestSecurityService testSecurityService;

    @PostMapping("/login")
    public AccountModel login(@RequestBody AccountModel accountModel, HttpSession httpSession) {
        return this.testSecurityService.login(accountModel.getUserName(), accountModel.getPassword(), AccountModel.class, httpSession);
    }

    @PostMapping("/join")
    public AccountModel createAccount(@RequestBody AccountModel accountModel) {
        return this.accountService.createAccount(accountModel);
    }

    @GetMapping("/session")
    public TestUserDetail sessionCheck(TestUserDetail testUserDetail) {
        return testUserDetail;
    }

}
