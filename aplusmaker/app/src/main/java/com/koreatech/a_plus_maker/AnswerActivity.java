package com.koreatech.a_plus_maker;

import android.os.Bundle;
import android.widget.TextView;

public class AnswerActivity extends ActivityBase {

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