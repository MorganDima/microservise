package com.example.rentme_backend_morgan.security.accountChecks;


import com.example.rentme_backend_morgan.business.repo.OwnerRepo;
import com.example.rentme_backend_morgan.business.repo.RenterRepo;
import com.example.rentme_backend_morgan.security.services.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import com.example.rentme_backend_morgan.security.config.AccountingParameters;
import com.example.rentme_backend_morgan.security.entities.Account;
import com.example.rentme_backend_morgan.security.repo.AccountRepo;

import javax.annotation.PostConstruct;
import java.util.*;

import static com.example.rentme_backend_morgan.security.api.AccountingResponses.*;

@Component
@Slf4j
public class AccountChecks extends RuntimeException {

    @Autowired
    AccountRepo autowiredAccountRepo;
    @Autowired
    OwnerRepo autowiredOwnerRepo;
    @Autowired
    RenterRepo autowiredRenterRepo;
    @Autowired
    PasswordEncoder autowiredEncoder;
    @Autowired
    AccountingParameters autowiredAccountingParameters;

    private static AccountRepo accountRepo;
    private static OwnerRepo ownerRepo;
    private static RenterRepo renterRepo;
    private static PasswordEncoder encoder;
    private static AccountingParameters parameters;
    private static List<String> allowedRoles;


    @PostConstruct
    private void plugAutowired() {
        ownerRepo = autowiredOwnerRepo;
        renterRepo = autowiredRenterRepo;
        accountRepo = autowiredAccountRepo;
        encoder = autowiredEncoder;
        parameters = autowiredAccountingParameters;
        allowedRoles = Arrays.asList(parameters.getAllowedRoles());
    }

    public static boolean checkIsRevoked() {

        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepo.findById(login).orElse(null);
        if (account.isRevoked())
            return true;

        return false;
    }

    public static void checkIsOwnerExists(String login) {
        if (renterRepo.existsById(login))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ACCOUNT_DOESNT_EXIST);
    }

    public static void checkIsRenterExists(String login) {
        if (ownerRepo.existsById(login))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ACCOUNT_DOESNT_EXIST);
    }

    public static void checkLogin(String login) {
        if (login.length() < parameters.getLoginLengthMin() || login.length() > parameters.getLoginLengthMax()
                || login.contains(" ") || !login.matches("[A-Za-z0-9]+"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, WRONG_USERNAME);
    }

    public static void checkLoginTheSame(String login, String loginEnter) {
        if (!login.equals(loginEnter))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, WRONG_USERNAME);
    }

    public static void checkPassword(String password) {
        if (password == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, PASSWORD_IS_NULL);
        }

        if (password.length() < parameters.getPasswordLength()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, WRONG_PASSWORD);
        }
    }

    public static void checkUserName(String userName) {
        if (userName == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, WRONG_USERNAME);
    }

    public static void checkRole(String role) {
        if (role == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, WRONG_ROLE);

    }

    public static void checkIsRoleAllowed(String role) {
        if (!allowedRoles.contains(role))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ROLE_NOT_ALLOWED);
    }

    public static void checkRoles(Set<String> roles) {
        if (roles == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ROLES_SET_IS_NULL);
    }

    public static void checkDto(Object dto) {
        if (dto == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, DTO_IS_NULL);
    }

    public static void checkAccountAbsence(String login) {
        checkUserName(login);
        if (accountRepo.existsById(login)) {
            throw new BadRequestException("Login " + login + " is exist");
        }
    }

    public static void checkByRole(String role) {
        if (accountRepo.existsByRole("ROLE_"+role)) {
            throw new BadRequestException("Role " + role + " is exist");
        }
        System.out.println(accountRepo.existsByRole(role)+" password");
    }


    public static Account getAccount(String userName) {
        checkUserName(userName);

        Account account = accountRepo.findById(userName).orElse(null);
        if (account == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ACCOUNT_DOESNT_EXIST);
        return account;
    }

    public static Account getActiveAccount(String userName) {
        Account account = getAccount(userName);
        if (account.isRevoked())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ACCOUNT_IS_REVOKED);
        return account;
    }

    public static Account getRevokedAccount(String userName) {
        Account account = getAccount(userName);
        if (!account.isRevoked())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ACCOUNT_IS_ACTIVE);
        return account;
    }

    public static void checkCurrentPasword(String password, String currentHash) {
        if (encoder.matches(password, currentHash))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, PASSWORD_IS_USING_NOW);

    }

    public static void checkUsedPassword(String password, Deque<String> used) { //TODO Deque dleete or no
        if (!used.stream().noneMatch(hash -> encoder.matches(password, hash)))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, PASSWORD_HAD_BEEN_ALREADY_USED);

    }

    public static void isRolePresent(HashSet<String> roles, String role) {
        if (!roles.contains(role))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ROLE_DOESNT_PRESENT);

    }

    public static void isRoleAbsent(Set<String> roles, String role) {
        if (roles.contains(role))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ROLE_IS_ALREADY_PRESENT);

    }

    public static void isRoleDefault(String role) {
        if (role.equals(parameters.getDefaultRole()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ROLE_IS_DEFAULT);
    }
}