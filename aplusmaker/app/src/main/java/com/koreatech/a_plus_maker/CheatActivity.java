package com.koreatech.a_plus_maker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CheatActivity extends ActivityBase implements CheatAdapter.RecyclerViewClicked {
    private RecyclerView cheatNoteRecyclerview;
    private LinearLayoutManager linearLayoutManager;
    private CheatAdapter cheatAdapter;
    private ArrayList<String> titleArrayList;
    private FileList fileList;
    private Handler updateUIHandler;

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
        updateUIHandler = new Handler();
        titleArrayList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateRecyclerview();
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
                clickedDeleteButton(position);
                break;
            case R.id.cheat_sheet_rewind_button:
                Toast.makeText(getApplicationContext(), titleArrayList.get(position) + "복습을 클릭 하였습니다.", Toast.LENGTH_SHORT).show();
                clickedRewindButton(position);
                break;
        }
    }

    public void updateRecyclerview() {
        showProgressDialog();
        new Thread(new Runnable() {
            @Override
            public void run() {
                fileList = SaveFileSharedPrefernce.getInstance().loadFileList();
                titleArrayList.clear();
                titleArrayList.addAll(fileList.getTitleList());
                updateUIHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        cheatAdapter.notifyDataSetChanged();
                        hideProgressDialog();
                    }
                });
            }
        }).start();


    }


    public void clickedDeleteButton(final int position) {
        showProgressDialog();
        new Thread(new Runnable() {
            @Override
            public void run() {
                fileList.deleteFile(position);
                SaveFileSharedPrefernce.getInstance().saveFileList(fileList);
                updateUIHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        hideProgressDialog();
                        updateRecyclerview();
                    }
                });
            }
        }
        ).start();
    }

    public void clickedRewindButton(final int position) {
        Intent intent = new Intent(CheatActivity.this, StudyActivity.class);
        intent.putExtra("TITLE", fileList.getTitle(position));
        intent.putExtra("CONTENT", fileList.getContent(position));
        startActivity(intent);
    }
}
