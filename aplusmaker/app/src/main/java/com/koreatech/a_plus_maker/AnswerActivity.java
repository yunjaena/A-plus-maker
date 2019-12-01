package com.koreatech.a_plus_maker;

import android.os.Bundle;
import android.widget.TextView;

public class AnswerActivity extends ActivityBase {

    private TextView answerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        String answer = getIntent().getStringExtra("answer");
        String question = getIntent().getStringExtra("question");
        answerTextView = findViewById(R.id.answer);
        answerTextView.setText(answer);

        if (getSupportActionBar() != null && question != null) {
            getSupportActionBar().setTitle(question);
        }
    }

}