package com.example.rentme_backend_morgan.security.repo;

import com.example.rentme_backend_morgan.security.entities.Account;
import com.example.rentme_backend_morgan.security.entities.Password;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class AccountRepoTest {

    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private PasswordRepo passwordRepo;

//    @Autowired
//    private PasswordEncoder encoder;

    @Test
    void existsByRole() {
        String role_user = "ROLE_USER";
        Account account = new Account(
                "DimaEvaKris",
                role_user);
//        accountRepo.save(account);
        passwordRepo.save(new Password(account, "123456"));

        System.out.println(accountRepo.findAll().toString());
        boolean existsByRole = accountRepo.existsByRole(role_user);
//
        assertThat(existsByRole).isTrue();
    }
}