package ge.tbc.testautomation.data; // creating conflict 2

public class LoginConstants {
    public static String Login_URL = "https://saucedemo.com";
    public static String STANDART_USER = "standard_user";
    public static String LOCKED_OUT_USER = "locked_out_user";
    public static String SQL_PASSWORD_TAKE = "SELECT password FROM Users WHERE username = ?";
    public static String PASSWORD_COLUMN_LABEL = "password";
    public static String BANNED_USER_ERROR_MESSAGE = "Epic sadface: Sorry, this user has been locked out.";
}
