package com.koreatehc.a_plus_maker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button faqButton;
    private Button studyButton;
    private Button cheatButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }
    public void init(){
        faqButton = (Button)findViewById(R.id.faq);
        studyButton = (Button)findViewById(R.id.study);
        cheatButton = (Button)findViewById(R.id.cheat_button);
    }
    public void onClickFAQButton(View view){
        Intent intent = new Intent(getApplicationContext(),FaqActivity.class);
        startActivity(intent);
    }
    public void onClickStudyButton(View view){
        Intent intent = new Intent(getApplicationContext(),StudyActivity.class);
        startActivity(intent);
    }
    public void onClickCheatButton(View view){
        Intent intent = new Intent(getApplicationContext(),CheatActivity.class);
        startActivity(intent);
    }
}
