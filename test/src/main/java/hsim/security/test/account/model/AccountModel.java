package hsim.security.test.account.model;

import hsim.security.test.account.domain.Account;
import hsim.simple.security.util.ObjectGenerator;
import lombok.Data;

@Data
public class AccountModel {
    private Long id;
    private String userName;
    private String password;
    private String role;

    public static AccountModel convertFromEntity(Account account) {
        if (account == null) {
            return null;
        }
        return ObjectGenerator.defaultModelMapper().map(account, AccountModel.class);
    }
}
