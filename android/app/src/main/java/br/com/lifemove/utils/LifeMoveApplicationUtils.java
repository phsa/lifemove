package br.com.lifemove.utils;

import android.app.Application;
import android.content.Context;
import android.widget.EditText;

import androidx.core.graphics.drawable.DrawableCompat;

import br.com.lifemove.R;

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



    public static void setInputAppearance(EditText input, int firstColor, int secondColor) {
        input.setTextColor(appContext.getResources().getColor(firstColor));
        DrawableCompat.setTint(input.getBackground(), appContext.getResources().getColor(secondColor));
    }

    public static void setInputAlrightAppearance(EditText input) {
        setInputAppearance(input, R.color.white, R.color.highlight);
    }

    public static void setInputSomethingWrongAppearance(EditText input) {
        setInputAppearance(input, R.color.red, R.color.red);
    }
}
