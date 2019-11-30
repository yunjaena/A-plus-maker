package com.koreatech.a_plus_maker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class FaqActivity extends ActivityBase {
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    RecyclerViewAdapter mAdapter;
    Button ClickButton;
    ArrayList<FandQItem> inquireFaq;
    ArrayList<FandQItem> gameModeFaq;
    ArrayList<FandQItem> wrongAnswerNoteFaq;
    ArrayList<FandQItem> generalModeFaq;
    ArrayList<FandQItem> randomModeFaq;
    ArrayList<FandQItem> gamBBackFaq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        ClickButton = (Button) findViewById(R.id.btn_문의하기);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // ArrayList 에 FandQItem 객체(데이터) 넣기
        inquireFaq = new ArrayList();
        gameModeFaq = new ArrayList();
        wrongAnswerNoteFaq = new ArrayList();
        generalModeFaq = new ArrayList();
        randomModeFaq = new ArrayList();
        gamBBackFaq = new ArrayList();

        inquireFaq.add(new FandQItem("Q. 문의하지마", "문의하지마 답변"));
        inquireFaq.add(new FandQItem("Q. 문의그만", "문의그만 답변"));
        gameModeFaq.add(new FandQItem("Q. 게임모드도그만", "게임모드도그만 답변"));
        gameModeFaq.add(new FandQItem("Q. 게임모드ㄴㄴ", "게임모드ㄴㄴ 답변"));
        wrongAnswerNoteFaq.add(new FandQItem("Q. 오답노트쓰지마","오답노트쓰지마 답변"));
        wrongAnswerNoteFaq.add(new FandQItem("Q. 오답노트 nono","오답노트 nono 답변"));
        generalModeFaq.add(new FandQItem("Q. 일반모드왜못해","일반모드왜못해 답변"));
        generalModeFaq.add(new FandQItem("Q. 일반모드면걍해","일반모드면 걍해 답변"));
        randomModeFaq.add(new FandQItem("Q. 랜덤모드니까막해","랜덤모드니까막해 답변"));
        randomModeFaq.add(new FandQItem("Q. 랜덤모드가어렵나","랜덤모드가어렵나 답변"));
        gamBBackFaq.add(new FandQItem("Q. 깜빡이는어려울만해","깜빡이는어려울만해 답변"));
        gamBBackFaq.add(new FandQItem("Q. 깜빡깜빡1","깜빡깜빡1 답변"));
        gamBBackFaq.add(new FandQItem("Q. 깜빡깜빡2","깜빡깜빡2 답변"));
        gamBBackFaq.add(new FandQItem("Q. 깜빡깜빡3","깜빡깜빡3 답변"));
        gamBBackFaq.add(new FandQItem("Q. 깜빡깜빡4","깜빡깜빡4 답변"));
        gamBBackFaq.add(new FandQItem("Q. 깜빡깜빡5","깜빡깜빡5 답변"));
        gamBBackFaq.add(new FandQItem("Q. 깜빡깜빡6","깜빡깜빡6 답변"));
        gamBBackFaq.add(new FandQItem("Q. 깜빡깜빡7","깜빡깜빡7 답변"));
        gamBBackFaq.add(new FandQItem("Q. 깜빡깜빡8","깜빡깜빡8 답변"));


        // LinearLayout으로 설정
        mRecyclerView.setLayoutManager(mLayoutManager);
        // Animation Defualt 설정
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // Decoration 설정
        // mRecyclerView.addItemDecoration(new RecyclerViewDecoration(this, RecyclerViewDecoration.VERTICAL_LIST));
        // Adapter 생성

    }

    public void onClick(View view) {
        showProgressDialog();
        ClickButton.setBackground(getResources().getDrawable(R.drawable.faq_button_unchecked));
        ClickButton = (Button) findViewById(view.getId());

       switch(view.getId()) {
            case R.id.btn_문의하기:
                ClickButton.setBackground(getResources().getDrawable(R.drawable.faq_button_checked));
                mAdapter = new RecyclerViewAdapter(inquireFaq);
                mRecyclerView.setAdapter(mAdapter);
                break;
            case R.id.btn_게임모드:
                ClickButton.setBackground(getResources().getDrawable(R.drawable.faq_button_checked));
                mAdapter = new RecyclerViewAdapter(gameModeFaq);
                mRecyclerView.setAdapter(mAdapter);
                break;
            case R.id.btn_오답노트:
                ClickButton.setBackground(getResources().getDrawable(R.drawable.faq_button_checked));
                mAdapter = new RecyclerViewAdapter(wrongAnswerNoteFaq);
                mRecyclerView.setAdapter(mAdapter);
                break;
            case R.id.btn_일반모드:
                ClickButton.setBackground(getResources().getDrawable(R.drawable.faq_button_checked));
                mAdapter = new RecyclerViewAdapter(generalModeFaq);
                mRecyclerView.setAdapter(mAdapter);
                break;
            case R.id.btn_랜덤모드:
                ClickButton.setBackground(getResources().getDrawable(R.drawable.faq_button_checked));
                mAdapter = new RecyclerViewAdapter(randomModeFaq);
                mRecyclerView.setAdapter(mAdapter);
           break;
            case R.id.btn_깜빡이모드:
                ClickButton.setBackground(getResources().getDrawable(R.drawable.faq_button_checked));
                mAdapter = new RecyclerViewAdapter(gamBBackFaq);
                mRecyclerView.setAdapter(mAdapter);
                break;

        }
        hideProgressDialog();
    }
}
