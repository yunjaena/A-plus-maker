package com.koreatech.a_plus_maker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class FaqActivity extends ActivityBase {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private FAQRecyclerViewAdapter mAdapter;
    private Button ClickButton;
    private ArrayList<FandQItem> appUsageFaq;
    private ArrayList<FandQItem> ttsModeFaq;
    private ArrayList<FandQItem> wrongAnswerNoteFaq;
    private ArrayList<FandQItem> generalModeFaq;
    private ArrayList<FandQItem> randomModeFaq;
    private ArrayList<FandQItem> gamBBackFaq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        ClickButton = (Button) findViewById(R.id.btn_ask);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // ArrayList 에 FandQItem 객체(데이터) 넣기
        appUsageFaq = new ArrayList();
        ttsModeFaq = new ArrayList();
        wrongAnswerNoteFaq = new ArrayList();
        generalModeFaq = new ArrayList();
        randomModeFaq = new ArrayList();
        gamBBackFaq = new ArrayList();

        appUsageFaq.add(new FandQItem("Q. 어떤 방식으로 학습이 진행되나요?", "A. 원하는 학습내용을 텍스트파일에 작성하여 휴대폰 기기에 저장합니다. 그리고 해당 파일을 앱으로 Load한 뒤 모드를 선택하면 학습이 진행됩니다. 각 모드별로 파일 텍스트를 작성하는 규칙들이 있고 이에 대한 내용은 각 모드 별 FAQ에 설명되어있습니다."));
        appUsageFaq.add(new FandQItem("Q. 텍스트 파일을 저장할 때 주의할 점이 있나요?", "A. 항상 파일 확장자는 \".txt\"이여야만 하고 다른 파일 형식은 불러올 수 없습니다.  또 모드 각각의 규칙에 맞게 텍스트 파일을 작성해야 원하는 학습이 가능합니다."));
        ttsModeFaq.add(new FandQItem("Q. tts모드는 어떤 모드이죠?", "A. 음성을 활용한 학습모드입니다. 텍스트를 작성하면 그 파일을 기계가 읽어줍니다. "));
        ttsModeFaq.add(new FandQItem("Q. 텍스트 파일을 작성하는 규칙이 있나요?", "A. 저장한 텍스트 파일 내용을 모두 읽어주기 때문에 따로 작성방법은 없습니다."));
        wrongAnswerNoteFaq.add(new FandQItem("Q. 오답노트쓰지마", "오답노트쓰지마 답변"));
        wrongAnswerNoteFaq.add(new FandQItem("Q. 오답노트 nono", "오답노트 nono 답변"));
        generalModeFaq.add(new FandQItem("Q. 1단계, 2단계, 3단계가 각각 어떻게 다르죠?", "A. 1단계는 초기에 학습을 위해 '{'와 '[' 괄호만을 없앤 단계입니다. 2단계는 '{'는 모두 없애고 '[' 괄호안에 텍스트만을 빈칸처리 한 단계입니다. 마지막으로 3단계는 '{' 안에 내용을 제외하고 모두 빈칸 처리하는 단계입니다."));
        generalModeFaq.add(new FandQItem("Q. 텍스트 파일을 작성하는 규칙이 있나요?", "A. 핵심단어나 외워야 할 필요가 있다고 생각하는 단어 양옆으로 '[' ']'를 적어주면 2단계에서 안쪽을 빈칸처리해줍니다. 또 단어가 아닌 문장 전체를 빈칸 처리 하고 싶으면 '{' 괄호를 이용하면됩니다. '{'괄호 안쪽을 제외한 나머지부분을 빈칸 처리 해줍니다. "));
        randomModeFaq.add(new FandQItem("Q. 랜덤모드니까막해", "랜덤모드니까막해 답변"));
        randomModeFaq.add(new FandQItem("Q. 랜덤모드가어렵나", "랜덤모드가어렵나 답변"));
        gamBBackFaq.add(new FandQItem("Q. 깜빡이는어려울만해", "깜빡이는어려울만해 답변"));
        gamBBackFaq.add(new FandQItem("Q. 깜빡깜빡1", "깜빡깜빡1 답변"));
        gamBBackFaq.add(new FandQItem("Q. 깜빡깜빡2", "깜빡깜빡2 답변"));
        gamBBackFaq.add(new FandQItem("Q. 깜빡깜빡3", "깜빡깜빡3 답변"));
        gamBBackFaq.add(new FandQItem("Q. 깜빡깜빡4", "깜빡깜빡4 답변"));
        gamBBackFaq.add(new FandQItem("Q. 깜빡깜빡5", "깜빡깜빡5 답변"));
        gamBBackFaq.add(new FandQItem("Q. 깜빡깜빡6", "깜빡깜빡6 답변"));
        gamBBackFaq.add(new FandQItem("Q. 깜빡깜빡7", "깜빡깜빡7 답변"));
        gamBBackFaq.add(new FandQItem("Q. 깜빡깜빡8", "깜빡깜빡8 답변"));


        // LinearLayout으로 설정
        mRecyclerView.setLayoutManager(mLayoutManager);
        // Animation Defualt 설정
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // Decoration 설정
        // mRecyclerView.addItemDecoration(new RecyclerViewDecoration(this, RecyclerViewDecoration.VERTICAL_LIST));
        // Adapter 생성

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("FAQ");
        }
    }

    public void onClick(View view) {
        showProgressDialog();
        ClickButton.setBackground(getResources().getDrawable(R.drawable.faq_button_unchecked));
        ClickButton = (Button) findViewById(view.getId());

        switch (view.getId()) {
            case R.id.btn_ask:
                ClickButton.setBackground(getResources().getDrawable(R.drawable.faq_button_checked));
                mAdapter = new FAQRecyclerViewAdapter(appUsageFaq);
                mRecyclerView.setAdapter(mAdapter);
                break;
            case R.id.btn_game_mode:
                ClickButton.setBackground(getResources().getDrawable(R.drawable.faq_button_checked));
                mAdapter = new FAQRecyclerViewAdapter(ttsModeFaq);
                mRecyclerView.setAdapter(mAdapter);
                break;
            case R.id.btn_feedback:
                ClickButton.setBackground(getResources().getDrawable(R.drawable.faq_button_checked));
                mAdapter = new FAQRecyclerViewAdapter(wrongAnswerNoteFaq);
                mRecyclerView.setAdapter(mAdapter);
                break;
            case R.id.btn_normal:
                ClickButton.setBackground(getResources().getDrawable(R.drawable.faq_button_checked));
                mAdapter = new FAQRecyclerViewAdapter(generalModeFaq);
                mRecyclerView.setAdapter(mAdapter);
                break;
            case R.id.btn_random:
                ClickButton.setBackground(getResources().getDrawable(R.drawable.faq_button_checked));
                mAdapter = new FAQRecyclerViewAdapter(randomModeFaq);
                mRecyclerView.setAdapter(mAdapter);
                break;
            case R.id.btn_blink:
                ClickButton.setBackground(getResources().getDrawable(R.drawable.faq_button_checked));
                mAdapter = new FAQRecyclerViewAdapter(gamBBackFaq);
                mRecyclerView.setAdapter(mAdapter);
                break;

        }
        hideProgressDialog();
    }
}
