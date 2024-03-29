package br.com.lifemove.utils;

public class StringUtils {

    public static final String EMPTY = "";
    public static final String DEBUG_TAG = "DebuggingLifeMove";


    public static final String SPORTS_EVENT_KEY = "event";


    public static final String INVALID_USERNAME_PATTERN = "[^a-zA-Z_.0-9\\-]";


    public static boolean checkEmail(String email) {
        if (email != null) return email.matches("^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+(\\.[a-z]+)?$");
        else return false;
    }

    public static String valueOf(int resID) {
        return LifeMoveApplicationUtils.getLifeMoveAppContext().getString(resID);
    }


}
