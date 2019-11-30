package com.koreatehc.a_plus_maker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CheatActivity extends ActivityBase implements CheatAdapter.RecyclerViewClicked {
    private RecyclerView cheatNoteRecyclerview;
    private LinearLayoutManager linearLayoutManager;
    private CheatAdapter cheatAdapter;
    private ArrayList<String> titleArrayList;
    private FileList fileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        init();
    }

    public void init() {
        fileList = SaveFileSharedPrefernce.getInstance().loadFileList();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.cheat_sheet);
        }

        initVariable();
        initView();
        initRecyclerView();
    }

    public void initVariable() {
        titleArrayList = new ArrayList<>();
        titleArrayList.addAll(fileList.getTitleList());
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
        showProgressDialog();
        switch (id) {
            case R.id.cheat_sheet_delete_button:
                Toast.makeText(getApplicationContext(), titleArrayList.get(position) + "삭제를 클릭 하였습니다.", Toast.LENGTH_SHORT).show();
                fileList.deleteFile(position);
                SaveFileSharedPrefernce.getInstance().saveFileList(fileList);
                updateRecyclerview();
                break;
            case R.id.cheat_sheet_rewind_button:
                Toast.makeText(getApplicationContext(), titleArrayList.get(position) + "복습을 클릭 하였습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CheatActivity.this, StudyActivity.class);
                intent.putExtra("TITLE", fileList.getTitle(position));
                intent.putExtra("CONTENT", fileList.getContent(position));
                startActivity(intent);
                break;
        }
        hideProgressDialog();
    }

    public void updateRecyclerview() {
        titleArrayList.clear();
        titleArrayList.addAll(fileList.getTitleList());
        cheatAdapter.notifyDataSetChanged();
    }
}
