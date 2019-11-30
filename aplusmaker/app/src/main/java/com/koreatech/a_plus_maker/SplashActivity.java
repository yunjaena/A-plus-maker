package com.koreatech.a_plus_maker;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.UiThread;

public class SplashActivity extends ActivityBase {
    private ImageView splashImageView;
    private TextView splashTextview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        splashAnimation();
    }

    public void initView() {
        splashImageView = findViewById(R.id.splash_imageView);
        splashTextview = findViewById(R.id.splash_textview);
    }

    @UiThread
    public void splashAnimation() {
        Animation textAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_splash_textview);
        Animation imageAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_splash_imageview);
        splashTextview.setAnimation(textAnimation);
        splashImageView.setAnimation(imageAnimation);
        textAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    }
                }).start();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
