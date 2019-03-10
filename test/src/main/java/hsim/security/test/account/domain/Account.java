package hsim.security.test.account.domain;

import hsim.security.test.account.model.AccountModel;
import hsim.simple.security.util.ObjectGenerator;
import hsim.simple.security.util.SimplePasswordEncoder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private String userName;
    private String password;
    private String role;

    public static Account createFromModel(AccountModel accountModel) {
        assert accountModel != null;

        Account account = ObjectGenerator.defaultModelMapper().map(accountModel, Account.class);
        account.setPassword(SimplePasswordEncoder.encodePassword(accountModel.getPassword()));
        return account;
    }


}
