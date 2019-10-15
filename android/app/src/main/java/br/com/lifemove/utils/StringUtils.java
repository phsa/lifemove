package br.com.lifemove.utils;

import android.util.Log;

public class StringUtils {

    private static String EMAIL_PATTERN = "^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+(\\.[a-z]+)?$";


    public static boolean checkEmail(String email) {
        if (email != null) return email.matches(EMAIL_PATTERN);
        else return false;
    }


}
