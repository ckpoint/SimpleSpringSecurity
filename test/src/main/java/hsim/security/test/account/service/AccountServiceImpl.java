package hsim.security.test.account.service;

import hsim.security.test.account.domain.Account;
import hsim.security.test.account.model.AccountModel;
import hsim.security.test.account.repository.AccountRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    @NonNull
    private final AccountRepository accountRepository;

    @Override
    public AccountModel findAccountFromUserName(String userName) {
        return AccountModel.convertFromEntity(this.accountRepository.findFirstByUserName(userName));
    }

    @Override
    public AccountModel createAccount(AccountModel accountModel) {

        AccountModel exist = this.findAccountFromUserName(accountModel.getUserName());
        if (exist != null) {
            throw new DuplicateKeyException("aleay exist userName");
        }

        Account joinUser = Account.createFromModel(accountModel);

        return AccountModel.convertFromEntity(this.accountRepository.save(joinUser));
    }
}
