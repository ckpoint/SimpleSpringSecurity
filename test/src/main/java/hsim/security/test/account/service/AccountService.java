package hsim.security.test.account.service;

import hsim.security.test.account.model.AccountModel;

public interface AccountService {

    AccountModel findAccountFromUserName(String userName);

    AccountModel createAccount(AccountModel accountModel);
}
