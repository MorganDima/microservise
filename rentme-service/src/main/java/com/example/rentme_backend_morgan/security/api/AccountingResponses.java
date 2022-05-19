package com.example.rentme_backend_morgan.security.api;

public interface AccountingResponses {
	
	public static String OK = "OK";
	public static String SOMETHING_GONE_WRONG = "Unclassified error";
	
	public static String WRONG_USERNAME = "Username doesnt match requirements or null";
	public static String USERNAME_THE_SAME = "Username the same";

	public static String ACCOUNT_CREATED = "Account created";
	public static String ACCOUNT_REMOVED = "Account removed";
	public static String ACCOUNT_ALREADY_EXISTS = "Username already registered"; 
	public static String ACCOUNT_DOESNT_EXIST = "Account doesnt exist";
	public static String ACCOUNT_HAD_BEEN_ACTIVATED = "Account had been activated";
	public static String ACCOUNT_IS_ACTIVE = "Account is already active";
	public static String ACCOUNT_HAD_BEEN_REVOKED = "Account had been revoked";
	public static String ACCOUNT_IS_REVOKED = "Account is already revoked";
	public static String ACCOUNT_IS_REFRESHED = "Account is refreshed";
	
	public static String ROLES_SET_IS_NULL = "Reles set is null";
	public static String WRONG_ROLE = "Role doesnt match requirements or null";
	public static String ROLE_NOT_ALLOWED = "Role doesnt match requirements or null";
	public static String ROLE_IS_ALREADY_PRESENT = "Role is already present"; 
	public static String ROLE_DOESNT_PRESENT = "Role doesnt present";
	public static String ROLE_IS_ADDED = "Role is granted";
	public static String ROLE_IS_REMOVED = "Role is deprived";
	public static String ROLE_IS_DEFAULT = "Default role cannot be removed";
	
	public static String PASSWORD_IS_NULL = "Password doesnt match requirements or null";
	public static String WRONG_PASSWORD = "Wrong login-password pair";
	public static String PASSWORD_EXPIRED = "Password expired";
	public static String PASSWORD_HAD_BEEN_ALREADY_USED = "Password had been alraedy used"; 
	public static String PASSWORD_IS_USING_NOW = "Password is using now";
	public static String PASSWORD_UPDATED = "Password is updated";
	
	public static String DTO_IS_NULL = "DTO instance is null";
	public static String LOGIN_IS_NOT_VALID = "Login is not valid";

}
