package com.koreatehc.a_plus_maker;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnswerActivity extends AppCompatActivity {

TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        String answer = getIntent().getStringExtra("answer");
        textView = (TextView) findViewById(R.id.answer);
        textView.setText(answer);
    }

}