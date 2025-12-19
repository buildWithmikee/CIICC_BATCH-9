package BankSystem.repository;

import java.util.List;

public interface UserRepository {
    void save(Account account);
    Account findByEmail(String email);
    List<Account> findAll();
}
