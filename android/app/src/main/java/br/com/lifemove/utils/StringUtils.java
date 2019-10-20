package br.com.lifemove.utils;

public class StringUtils {

    public static final String EMPTY = "";
    private static String EMAIL_PATTERN = "^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+(\\.[a-z]+)?$";


    public static boolean checkEmail(String email) {
        if (email != null) return email.matches(EMAIL_PATTERN);
        else return false;
    }

    public static String valueOf(int resID) {
        return LifeMoveApplicationUtils.getLifeMoveAppContext().getString(resID);
    }


}
