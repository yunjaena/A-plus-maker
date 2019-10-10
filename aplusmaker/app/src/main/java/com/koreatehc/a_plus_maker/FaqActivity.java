package com.koreatehc.a_plus_maker;

import android.graphics.Color;
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
    ArrayList<Item> inquireFaq;
    ArrayList<Item> gameModeFaq;
    ArrayList<Item> wrongAnswerNoteFaq;
    ArrayList<Item> generalModeFaq;
    ArrayList<Item> randomModeFaq;
    ArrayList<Item> gamBBackFaq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        ClickButton = (Button) findViewById(R.id.btn_문의하기);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // ArrayList 에 Item 객체(데이터) 넣기
        inquireFaq = new ArrayList();
        gameModeFaq = new ArrayList();
        wrongAnswerNoteFaq = new ArrayList();
        generalModeFaq = new ArrayList();
        randomModeFaq = new ArrayList();
        gamBBackFaq = new ArrayList();

        inquireFaq.add(new Item("Q. 문의하지마", 1));
        inquireFaq.add(new Item("Q. 문의그만", 2));
        gameModeFaq.add(new Item("Q. 게임모드도그만", 3));
        gameModeFaq.add(new Item("Q. 게임모드ㄴㄴ", 4));
        wrongAnswerNoteFaq.add(new Item("Q. 오답노트쓰지마",5));
        wrongAnswerNoteFaq.add(new Item("Q. 오답노트 nono",6));
        generalModeFaq.add(new Item("Q. 일반모드왜못해",7));
        generalModeFaq.add(new Item("Q. 일반모드면걍해",8));
        randomModeFaq.add(new Item("Q. 랜덤모드니까막해",9));
        randomModeFaq.add(new Item("Q. 랜덤모드가어렵나",10));
        gamBBackFaq.add(new Item("Q. 깜빡이는어려울만해",11));
        gamBBackFaq.add(new Item("Q. 깜빡깜빡1",12));
        gamBBackFaq.add(new Item("Q. 깜빡깜빡2",13));
        gamBBackFaq.add(new Item("Q. 깜빡깜빡3",14));
        gamBBackFaq.add(new Item("Q. 깜빡깜빡4",15));
        gamBBackFaq.add(new Item("Q. 깜빡깜빡5",16));
        gamBBackFaq.add(new Item("Q. 깜빡깜빡6",17));
        gamBBackFaq.add(new Item("Q. 깜빡깜빡7",18));
        gamBBackFaq.add(new Item("Q. 깜빡깜빡8",19));


        // LinearLayout으로 설정
        mRecyclerView.setLayoutManager(mLayoutManager);
        // Animation Defualt 설정
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // Decoration 설정
        // mRecyclerView.addItemDecoration(new RecyclerViewDecoration(this, RecyclerViewDecoration.VERTICAL_LIST));
        // Adapter 생성

    }

    public void onClick(View view) {
        ClickButton.setBackgroundColor(Color.rgb(121, 102, 255));
        ClickButton = (Button) findViewById(view.getId());

       switch(view.getId()) {
            case R.id.btn_문의하기:
                ClickButton.setBackgroundColor(Color.rgb(175, 164, 255));
                mAdapter = new RecyclerViewAdapter(inquireFaq);
                mRecyclerView.setAdapter(mAdapter);
                break;
            case R.id.btn_게임모드:
                ClickButton.setBackgroundColor(Color.rgb(175, 164, 255));
                mAdapter = new RecyclerViewAdapter(gameModeFaq);
                mRecyclerView.setAdapter(mAdapter);
                break;
            case R.id.btn_오답노트:
                ClickButton.setBackgroundColor(Color.rgb(175, 164, 255));
                mAdapter = new RecyclerViewAdapter(wrongAnswerNoteFaq);
                mRecyclerView.setAdapter(mAdapter);
                break;
            case R.id.btn_일반모드:
                ClickButton.setBackgroundColor(Color.rgb(175, 164, 255));
                mAdapter = new RecyclerViewAdapter(generalModeFaq);
                mRecyclerView.setAdapter(mAdapter);
                break;
            case R.id.btn_랜덤모드:
                ClickButton.setBackgroundColor(Color.rgb(175, 164, 255));
                mAdapter = new RecyclerViewAdapter(randomModeFaq);
                mRecyclerView.setAdapter(mAdapter);
           break;
            case R.id.btn_깜빡이모드:
                ClickButton.setBackgroundColor(Color.rgb(175, 164, 255));
                mAdapter = new RecyclerViewAdapter(gamBBackFaq);
                mRecyclerView.setAdapter(mAdapter);
                break;

        }
    }
}
