package hsim.security.test.account.repository;

import hsim.security.test.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findFirstByUserName(String userName);
}
