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
        ClickButton = findViewById(R.id.btn_ask);
        mRecyclerView = findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // ArrayList 에 FandQItem 객체(데이터) 넣기
        appUsageFaq = new ArrayList<>();
        ttsModeFaq = new ArrayList<>();
        wrongAnswerNoteFaq = new ArrayList<>();
        generalModeFaq = new ArrayList<>();
        randomModeFaq = new ArrayList<>();
        gamBBackFaq = new ArrayList<>();

        appUsageFaq.add(new FandQItem("Q. 어떤 방식으로 학습이 진행되나요?", "A. 원하는 학습내용을 텍스트파일에 작성하여 휴대폰 기기에 저장합니다. 그리고 해당 파일을 앱으로 Load한 뒤 모드를 선택하면 학습이 진행됩니다. 각 모드별로 파일 텍스트를 작성하는 규칙들이 있고 이에 대한 내용은 각 모드 별 FAQ에 설명되어있습니다."));
        appUsageFaq.add(new FandQItem("Q. 텍스트 파일을 저장할 때 주의할 점이 있나요?", "A. 항상 파일 확장자는 \".txt\"이여야만 하고 다른 파일 형식은 불러올 수 없습니다.  또 모드 각각의 규칙에 맞게 텍스트 파일을 작성해야 원하는 학습이 가능합니다."));
        ttsModeFaq.add(new FandQItem("Q. tts모드는 어떤 모드이죠?", "A. 음성을 활용한 학습모드입니다. 텍스트를 작성하면 그 파일을 기계가 읽어줍니다. "));
        ttsModeFaq.add(new FandQItem("Q. 텍스트 파일을 작성하는 규칙이 있나요?", "A. 저장한 텍스트 파일 내용을 모두 읽어주기 때문에 따로 작성방법은 없습니다."));
        generalModeFaq.add(new FandQItem("Q. 1단계, 2단계, 3단계가 각각 어떻게 다르죠?", "A. 1단계는 초기에 학습을 위해 '{'와 '[' 괄호만을 없앤 단계입니다. 2단계는 '{'는 모두 없애고 '[' 괄호안에 텍스트만을 빈칸처리 한 단계입니다. 마지막으로 3단계는 '{' 안에 내용을 제외하고 모두 빈칸 처리하는 단계입니다."));
        generalModeFaq.add(new FandQItem("Q. 텍스트 파일을 작성하는 규칙이 있나요?", "A. 핵심단어나 외워야 할 필요가 있다고 생각하는 단어 양옆으로 '[' ']'를 적어주면 2단계에서 안쪽을 빈칸처리해줍니다. 또 단어가 아닌 문장 전체를 빈칸 처리 하고 싶으면 '{' 괄호를 이용하면됩니다. '{'괄호 안쪽을 제외한 나머지부분을 빈칸 처리 해줍니다. "));
        wrongAnswerNoteFaq.add(new FandQItem("Q. 오답노트는 어떻게 등록하나요?",
                "A. 학습하기에서 학습을 할 때 복습하고 싶으면 우측 상단의 북마크를 눌러 해당 학습 파일이 오답노트에 등록합니다."));
        wrongAnswerNoteFaq.add(new FandQItem("Q. 오답노트는 어떻게 사용하나요?",
                "A. 메인메뉴에서 오답노트 아이콘을 눌러 오답노트 화면으로 전환한 후, 리스트에서 복습하고 싶은 파일명 옆의 복습 버튼을 누르면 해당 학습파일이 바로 불러와진 학습하기 화면으로 전환됩니다."));
        randomModeFaq.add(new FandQItem("Q. 랜덤모드가 뭔가요?",
                "A. 일반학습모드와 같이 학습파일에서 빈 칸을 뚫어주는 학습모드로, 순차적이고 단계적으로 빈 칸을 뚫어주는 것이 아닌 확률적으로 정해진 부분을 뚫어줍니다."));
        randomModeFaq.add(new FandQItem("Q. 랜덤모드에서 Level이란 무엇인가요?",
                "A. 화면에 보여지는 단계를 말하는 것으로 1단계는 모든 칸이 채워진 학습파일을 보여주고, 2단계는 정해진 부분의 40%, 3단계는 60%를 빈칸으로 뚫습니다."));
        gamBBackFaq.add(new FandQItem("Q. 깜빡이모드가 뭔가요?",
                "A. 기존의 영단어 깜빡이학습장의 학습원리를 바탕으로 한 학습모드로, 학습하고 내용을 양식에 맞게 작성하고 학습을 시작하게 되면 학습 내용이 구분자로 나뉘어 단어와 뜻을 2초 간격으로 깜빡이며 보여줍니다."));
        gamBBackFaq.add(new FandQItem("Q. 깜빡이모드 학습파일 양식이 어떻게 되나요?", "" +
                "A. 깜빡이모드의 경우 단어들과 거기에 맞는 짧은 설명을 연속해서 보여주므로 단어와 설명 사이의 구분자는 '/' 만을 사용합니다.\n" +
                "ex) 폭포수 모델/순차적인 소프트웨어 개발 프로세스/has-a/다른 객체를 받아들여 그 객체의 기능을 사용하는 관계/..."));
        ;


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
