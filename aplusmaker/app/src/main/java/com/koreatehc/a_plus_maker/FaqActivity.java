package com.koreatehc.a_plus_maker;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class FaqActivity extends AppCompatActivity {
    Button ClickButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        ClickButton = (Button) findViewById(R.id.btn_문의하기);
    }
    public void onClick(View view) {
        ClickButton.setBackgroundColor(Color.rgb(238, 102, 16));
        ClickButton = (Button) findViewById(view.getId());
       switch(view.getId()) {
            case R.id.btn_문의하기:
                ClickButton.setBackgroundColor(Color.RED);
                break;
            case R.id.btn_게임모드:
                ClickButton.setBackgroundColor(Color.RED);
                break;
            case R.id.btn_오답노트:
                ClickButton.setBackgroundColor(Color.RED);
                break;
            case R.id.btn_일반모드:
                ClickButton.setBackgroundColor(Color.RED);
                break;
            case R.id.btn_랜덤모드:
                ClickButton.setBackgroundColor(Color.RED);
           break;
            case R.id.btn_깜빡이모드:
                ClickButton.setBackgroundColor(Color.RED);
                break;

        }
    }
}
