package br.com.lifemove.utils;

public class SessionUtils {


    public static final String USER_KEY = "user";
    public static final String PASSWORD_KEY = "password";

    public static String loggedUsername() {
        return SharedPreferencesUtils.readOfSharedPreferences(USER_KEY);
    }

    public static void saveSession(String login, String password) {
        SharedPreferencesUtils.writeInSharedPreferences(SessionUtils.USER_KEY, login);
        SharedPreferencesUtils.writeInSharedPreferences(SessionUtils.PASSWORD_KEY, password);
    }

    public static boolean anyLoggedUser() {
        String username = loggedUsername();
        return username != null && !username.isEmpty();
    }

}
