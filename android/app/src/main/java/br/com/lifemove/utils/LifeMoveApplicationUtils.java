package br.com.lifemove.utils;

import android.app.Application;
import android.content.Context;

public class LifeMoveApplicationUtils extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    public static Context getLifeMoveAppContext() {
        return appContext;
    }
}
