package com.example.rentme_backend_morgan.security.services;


//import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

import com.example.rentme_backend_morgan.security.accountChecks.AccountChecks;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.example.rentme_backend_morgan.security.config.AccountingParameters;
import com.example.rentme_backend_morgan.security.dto.RegisterDto;
import com.example.rentme_backend_morgan.security.entities.Account;
import com.example.rentme_backend_morgan.security.entities.Password;
import com.example.rentme_backend_morgan.security.repo.AccountRepo;
import com.example.rentme_backend_morgan.security.repo.PasswordRepo;

import java.util.List;

import static com.example.rentme_backend_morgan.security.api.AccountingResponses.*;

@Service
@RequiredArgsConstructor
public class SecurityService implements ISecurityService {

    private final AccountRepo accountRepo;
    private final PasswordRepo passwordRepo;
    private final PasswordEncoder encoder;
    private final AddToSQL addProfileToSQL;
    private final AccountingParameters parameters;

    @Override
    @Transactional(transactionManager = "securityTransactionManager")
    public Account addProfile(RegisterDto dto) {

        AccountChecks.checkIsRoleAllowed(dto.getRole());
        AccountChecks.checkAccountAbsence(dto.getLogin());

//      AccountChecks.checkByRole(dto.getRole());

        Account account = new Account(
                dto.getLogin(),
                "ROLE_" + dto.getRole());

        passwordRepo.save(new Password(account, encoder.encode(dto.getPassword())));
        addProfileToSQL.addProfileToSQL(dto);
        return account;
    }


    @Override
    public synchronized Account addAccount(RegisterDto dto) {

        AccountChecks.checkIsRoleAllowed(dto.getRole());
        AccountChecks.checkAccountAbsence(dto.getLogin());

        Account account = new Account(
                dto.getLogin(),
                "ROLE_" + dto.getRole());
        accountRepo.save(account);

        passwordRepo.save(new Password(account, encoder.encode(dto.getPassword())));

        return account;
    }

    public Account getAccount(String login) {
        Account account = accountRepo.findById(login)
                .orElseThrow(() -> new BadRequestException("Login " + login + " not found"));
        return account;
    }

    @Override
    public synchronized Account grantRole(String login, String role) {

        AccountChecks.checkIsRoleAllowed(role);
        Account account = getAccount(login);
        account.setRole("ROLE_" + role);
        accountRepo.save(account);

        return account;
    }

//    @Override
//    public synchronized Account depriveRole(String login, String role) {
//        checkIsRoleAllowed(role);
//        Account account = getAccount(login);
//
//        if (roles.size() < 2)
//            throw new BadRequestException("Account " + login + " has only one role. Accounts without roles are not allowed.");
//        String prefixedRole = "ROLE_" + role;
//        if (!roles.contains(prefixedRole))
//            throw new BadRequestException("Account " + login + " has no role " + role);
//        roles.remove(prefixedRole);
//        accountRepo.save(account);
//
//        return account;
//    }

    @Override
    public String getRolesByLogin(String login) {
        return getAccount(login).getRole();
//                .map(role -> role.substring(5)).collect(Collectors.toSet());
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }

    @Override
    public Account removeAccount(String login) {

        Account account = getAccount(login);
        accountRepo.deleteById(login);

        return account; //TODO if we are deleted account, what we will returner?
    }

    @Override
    public Account removeUser(String login) {
        Account account = getAccount(login);

        if (account.getRole().contains("ROLE_USER"))
            throw new BadRequestException("Removal not authorized");

        accountRepo.deleteById(login);
        return account;
    }

    @Override
    public Account removeOwner(String login) {
        Account account = getAccount(login);

        if (account.getRole().contains("ROLE_USER"))
            throw new BadRequestException("Removal not authorized");

        accountRepo.deleteById(login);
        return account;
    }

    @Override
    public synchronized String changePassword(String login, String pass) {

        String loginEnter = SecurityContextHolder.getContext().getAuthentication().getName();
        AccountChecks.checkLoginTheSame(login, loginEnter);

        Password password = passwordRepo.findByAccount(null);

        if (password.getPassword().equals(pass))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, PASSWORD_HAD_BEEN_ALREADY_USED);
        password.setPassword(pass);

//        String currentPassword =
//        checkCurrentPasword(password, currentPassword);
//        LinkedList<String> oldPasswords = account.getOldPasswords(); //TODO maybe linkedlist
//        checkUsedPassword(password, oldPasswords);
//        if (oldPasswords.size() == parameters.getN_last_hashcodes())
//            oldPasswords.removeFirst();
//        oldPasswords.addLast(currentPassword);
//        account.setPassword(password);
        passwordRepo.save(password);
        return "Every thing is fine";
    }

    @Override
    public synchronized String revokeAccount(String login) {
        Account account = getAccount(login);
        if (account.isRevoked())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ACCOUNT_IS_REVOKED);
//        boolean revoked = true;
//        activateRevokeAccount(revoked, login, account);
        return "Account is revoked";
    }

    @Override
    public synchronized String activateAccount(String login) {
        Account account = getAccount(login);
        if (!account.isRevoked())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ACCOUNT_IS_REFRESHED);
//        boolean revoked = false;
//        activateRevokeAccount(revoked, login, account);
        return "Account is activated";
    }
//    @Transactional
//    public void activateRevokeAccount(boolean flag, String login, Account account) {
//        Set<String> roles = account.getRoles();
//
//        if (flag) {
//            if (roles.contains("ROLE_USER")) {
//                checkIsRenterExists(login);
//                account.setRevoked(true);
//                accountRepo.save(account);
//                renterRepo.revokedByLogin(true, login);
//
//            } else if (roles.contains("ROLE_OWNER")) {
//                checkIsOwnerExists(login);
//                account.setRevoked(true);
//                accountRepo.save(account);
//                ownerRepo.revokedByLogin(true, login);
//            }
//        } else {
//            if (roles.contains("ROLE_USER")) {
//                checkIsRenterExists(login);
//                account.setRevoked(false);
//                accountRepo.save(account);
//                renterRepo.revokedByLogin(false, login);
//
//            } else if (roles.contains("ROLE_OWNER")) {
//                checkIsOwnerExists(login);
//                account.setRevoked(false);
//                accountRepo.save(account);
//                ownerRepo.revokedByLogin(false, login);
//            }
//        }
//    }
}