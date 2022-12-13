package com.example.managerworkofstatecadres.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.hq.manager_work.R;


public class screenHello extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_hello);
        ImageView img = (ImageView) findViewById(R.id.imgHello);
        Animation aniSlide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoomhello);
        img.startAnimation(aniSlide);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(screenHello.this, screenLogin.class));
                finish();
            }
        }, 3000);
    }
}