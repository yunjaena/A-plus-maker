package com.koreatehc.a_plus_maker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class FaqActivity extends AppCompatActivity {
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

        wrongAnswerNoteFaq.add(new FandQItem("Q. 오답노트 등록방법?",
                "A. 학습하기에서 학습을 할 때 복습하고 싶으면 우측 상단의 북마크를 눌러 해당 학습 파일이 오답노트에 등록한다."));
        wrongAnswerNoteFaq.add(new FandQItem("Q. 오답노트 사용법?",
                "A. 메인메뉴에서 오답노트 아이콘을 눌러 오답노트 화면으로 전환한 후, 리스트에서 복습하고 싶은 파일명 옆의 복습 버튼을 누르면 해당 학습파일이 바로 불러와진 학습하기 화면으로 전환된다."));

        generalModeFaq.add(new FandQItem("Q. 일반모드왜못해","일반모드왜못해 답변"));
        generalModeFaq.add(new FandQItem("Q. 일반모드면걍해","일반모드면 걍해 답변"));

        randomModeFaq.add(new FandQItem("Q. 랜덤모드가 뭐죠?",
                "A. 일반학습모드와 같이 학습파일에서 빈 칸을 뚫어주는 학습모드로, 순차적이고 단계적으로 빈 칸을 뚫어주는 것이 아닌 확률적으로 정해진 부분을 뚫어준다."));
        randomModeFaq.add(new FandQItem("Q. 랜덤모드에서 Level이란?",
                "A. 화면에 보여지는 단계를 말하는 것으로 1단계는 모든 칸이 채워진 학습파일을 보여주고, 2단계는 정해진 부분의 40%, 3단계는 60%를 빈칸으로 뚫는다."));
        gamBBackFaq.add(new FandQItem("Q. 깜빡이모드가 뭐죠?",
                "A. 기존의 영단어 깜빡이학습장의 학습원리를 바탕으로 한 학습모드로, 학습하고 내용을 양식에 맞게 작성하고 학습을 시작하게 되면 학습 내용이 구분자로 나뉘어 단어와 뜻을 2초 간격으로 깜빡이며 보여준다."));
        gamBBackFaq.add(new FandQItem("Q. 깜빡이모드 학습파일 양식","" +
                "A. 깜빡이모드의 경우 단어들과 거기에 맞는 짧은 설명을 연속해서 보여주므로 단어와 설명 사이의 구분자는 '/' 만을 사용한다.\n" +
                "ex) 폭포수 모델/순차적인 소프트웨어 개발 프로세스/has-a/다른 객체를 받아들여 그 객체의 기능을 사용하는 관계/..."));



        // LinearLayout으로 설정
        mRecyclerView.setLayoutManager(mLayoutManager);
        // Animation Defualt 설정
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // Decoration 설정
        // mRecyclerView.addItemDecoration(new RecyclerViewDecoration(this, RecyclerViewDecoration.VERTICAL_LIST));
        // Adapter 생성

    }

    public void onClick(View view) {
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
    }
}
