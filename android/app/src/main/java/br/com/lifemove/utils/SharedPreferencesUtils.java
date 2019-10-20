package br.com.lifemove.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPreferencesUtils {

    public static final String USER_KEY = "user";
    public static final String PASSWORD_KEY = "password";
    private static final String PREFERENCE = "preference";

    public static void writeInSharedPreferences(String key, String value) {
        SharedPreferences preferences = LifeMoveApplicationUtils.getLifeMoveAppContext().getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        if (key != null) {
            if(value == null){
                value = StringUtils.EMPTY;
            }
            edit.putString(key, value);
            edit.apply();
        }
    }

    public static String readOfSharedPreferences(String key){
        SharedPreferences preferences = LifeMoveApplicationUtils.getLifeMoveAppContext().getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        return preferences.getString(String.valueOf(key), StringUtils.EMPTY);
    }

}
