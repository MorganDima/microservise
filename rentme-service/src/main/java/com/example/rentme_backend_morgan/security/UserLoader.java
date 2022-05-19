package com.example.rentme_backend_morgan.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.example.rentme_backend_morgan.security.entities.Account;
import com.example.rentme_backend_morgan.security.entities.Password;
import com.example.rentme_backend_morgan.security.repo.AccountRepo;
import com.example.rentme_backend_morgan.security.repo.PasswordRepo;

@Component
@RequiredArgsConstructor
public class UserLoader implements UserDetailsService {

    public final AccountRepo accountRepo;
    public final PasswordRepo passwordRepo;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Account account = accountRepo.findById(login).orElseThrow(() -> new UsernameNotFoundException("Login not registered"));

        Password password = passwordRepo.findByAccount(account);

        if (account.isRevoked()) throw new UsernameNotFoundException("Account revoked");

        String role = account.getRole();

        return new User(login,
                password.getPassword(),
                AuthorityUtils.createAuthorityList(role));
    }
}