package com.koreatehc.a_plus_maker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class CheatActivity extends AppCompatActivity implements CheatAdapter.RecyclerViewClicked {
    private RecyclerView cheatNoteRecyclerview;
    private LinearLayoutManager linearLayoutManager;
    private CheatAdapter cheatAdapter;
    private ArrayList<String> titleArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        init();
    }

    public void init() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.cheat_sheet);
        }

        initVariable();
        initView();
        initRecyclerView();
    }

    public void initVariable() {
        titleArrayList = new ArrayList<>();
        titleArrayList.add("언어의 온도");
        titleArrayList.add("소프트웨어 공학");
        titleArrayList.add("객체지향");
    }

    public void initView() {
        cheatNoteRecyclerview = findViewById(R.id.cheat_list_recyclerview);
    }

    public void initRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(this);
        cheatAdapter = new CheatAdapter(titleArrayList);
        cheatAdapter.setOnClickedRecyclerView(this);
        cheatNoteRecyclerview.setLayoutManager(linearLayoutManager);
        cheatNoteRecyclerview.setAdapter(cheatAdapter);
        cheatAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickedRecyclerView(int position, int id) {
        switch (id) {
            case R.id.cheat_sheet_delete_button:
                Toast.makeText(getApplicationContext(), titleArrayList.get(position) + "삭제를 클릭 하였습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cheat_sheet_rewind_button:
                Toast.makeText(getApplicationContext(), titleArrayList.get(position) + "복습을 클릭 하였습니다.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
