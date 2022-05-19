package com.example.rentme_backend_morgan.security.api;

public interface SecurityApi {


    public static String SECURITY = "/security";
    public static final String JWT = "/jwt";

    public static final String AUTHENTICATE = "/authenticate";
    public static final String REGISTER = "/register";

    public static String ADD_USER = "/addUser";
    public static String ADD_OWNER = "/addOwner";
    public static String REMOVE_USER = "/removeUser";
    public static String REMOVE_OWNER = "/removeOwner";

    public static String ADD_ACCOUNT = "/addAccount";
    public static String REMOVE_ACCOUNT = "/removeAccount";
    public static String GET_ALL_ACCOUNTS = "/getAllAccounts";
    public static String REVOKE_ACCOUNT = "/revokeAccount";
    public static String ACTIVATE_ACCOUNT = "/activateAccount";

    public static String CHANGE_PASSWORD = "changePassword";

    public static String GET_ROLE_BY_LOGIN = "/getRoleByLogin";
    public static String GRANT_ROLE = "/grantRole";
    public static String DEPRIVE_ROLE = "/depriveRole";

}