package com.example.loginauthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

// SPLASH SCREEN PAGE

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;
    //variables
    Animation topAnim, bottomAnim;
    ImageView imageView;
    TextView txtWelcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);


        imageView = findViewById(R.id.imageView);
        txtWelcome = findViewById(R.id.txtWelcome);

        imageView.setAnimation(topAnim);
        txtWelcome.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, WelcomePage.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);


    }
}