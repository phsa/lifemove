package br.com.lifemove.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import br.com.lifemove.R;
import br.com.lifemove.utils.SessionUtils;

public class SplashScreenActivity extends AppCompatActivity {


    public static final int TIME_SPLASH_SCREEN = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView logoSplashScreen = findViewById(R.id.logo_splash_screen);

        Animation animationTransition = AnimationUtils.loadAnimation(this, R.anim.animation_transition);
        logoSplashScreen.startAnimation(animationTransition);

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(SessionUtils.anyLoggedUser())
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                else
                    startActivity(new Intent(getApplicationContext(), PresentationActivity.class));
                finish();
            }
        }, TIME_SPLASH_SCREEN);

    }

}
