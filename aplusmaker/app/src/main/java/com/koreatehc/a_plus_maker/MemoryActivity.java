package com.koreatehc.a_plus_maker;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.koreatehc.a_plus_maker.studymode.StudyModeFactory;
import com.koreatehc.a_plus_maker.studymode.NormalStudyMode;

import java.util.ArrayList;

public class MemoryActivity extends AppCompatActivity {
    private Spinner levelSpinner;
    private ImageView saveImageview;
    private TextView contentTextview;
    private StudyModeFactory studyModeFactory;
    private boolean isSaved;

    private String content;
    private int mode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        init();
    }

    public void init() {
        isSaved = false;
        getData();
        initView();
        setMode();
    }

    private void initView() {
        levelSpinner = findViewById(R.id.memory_level_spinner);
        saveImageview = findViewById(R.id.memory_save_imageview);
        contentTextview = findViewById(R.id.memory_content_textview);
        saveImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedSaveButton();
            }
        });
    }

    public void setMode() {
        switch (mode) {
            case 0:
                studyModeFactory = new NormalStudyMode(content);
                break;
            case 1:
                studyModeFactory = new NormalStudyMode(content);
                break;
            case 2:
                studyModeFactory = new NormalStudyMode(content);
                break;
            case 3:
                studyModeFactory = new NormalStudyMode(content);
                break;
        }
        setSpinner();

    }

    public void clickedSaveButton() {
        int imageId;
        if (isSaved) {
            isSaved = false;
            imageId = R.drawable.ic_flag_uncheck;
        } else {
            isSaved = true;
            imageId = R.drawable.ic_flag_checked;
        }
        saveImageview.setBackground(getResources().getDrawable(imageId));
    }

    public void setSpinner() {
        if (studyModeFactory == null) return;
        int maxLevel = studyModeFactory.getStudyLevel();
        if (maxLevel == 0) {
            levelSpinner.setVisibility(View.GONE);
            return;
        }
        ArrayList<String> spinnerItem = new ArrayList<>();
        for (int i = 0; i < maxLevel; i++) {
            spinnerItem.add((i + 1) + "단계");
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerItem);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(arrayAdapter);
        levelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String contentString = studyModeFactory.getContent(position + 1);
                contentTextview.setText(contentString);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void getData() {
        if (getIntent() == null) {
            Toast.makeText(this, "데이터를 불러오지 못했습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }
        content = getIntent().getStringExtra("FILE");
        mode = getIntent().getIntExtra("MEMORY_MODE", 0);
    }
}
