package br.com.lifemove.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPreferencesUtils {

    private static final String EMPTY = "";

    public static void writeInSharedPreferences(String key, String value) {
        SharedPreferences preferences = LifeMoveApplicationUtils.getLifeMoveAppContext().getSharedPreferences("preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        if (key != null) {
            if(value == null){
                value = EMPTY;
            }
            edit.putString(key, value);
            edit.apply();
        }
    }

    public static String readOfSharedPreferences(String key){
        SharedPreferences preferences = LifeMoveApplicationUtils.getLifeMoveAppContext().getSharedPreferences("preference", Context.MODE_PRIVATE);
        String value = preferences.getString(String.valueOf(key), EMPTY);
        return value;
    }

}
