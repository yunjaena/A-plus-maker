package com.koreatehc.a_plus_maker;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.koreatehc.a_plus_maker.studymode.NormalStudyMode;
import com.koreatehc.a_plus_maker.studymode.RandomStudyMode;
import com.koreatehc.a_plus_maker.studymode.StudyModeFactory;
import com.koreatehc.a_plus_maker.studymode.TTSStudyMode;

import java.util.ArrayList;
import java.util.Locale;

public class MemoryActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    public static final int REQUEST_CODE_AUDIO_AND_WRITE_EXTERNAL_STORAGE = 1;
    private LinearLayout ttsLinearLayout;
    private Spinner levelSpinner;
    private ImageView saveImageview;
    private TextView contentTextview;
    private StudyModeFactory studyModeFactory;
    private boolean isSaved;
    private String content;
    private int mode;
    private boolean isTTSValid;
    private Button ttsPlayButton;
    private Button ttsStopButton;
    private TextToSpeech tts;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        init();
    }

    public void init() {
        tts = new TextToSpeech(this, this);
        isSaved = false;
        isTTSValid = false;
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
        ttsPlayButton = findViewById(R.id.memory_play_tts_button);
        ttsStopButton = findViewById(R.id.memory_stop_tts_button);
        ttsLinearLayout = findViewById(R.id.memory_tts_mode_linear_layout);
        ttsStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
            }
        });
        ttsLinearLayout.setVisibility(View.GONE);
        ttsPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReadTextButton();
            }
        });
        ttsPlayButton.setEnabled(false);
        ttsStopButton.setEnabled((false));
        setPlayButtonColor();
    }

    public void stopPlaying() {
        tts.stop();
    }


    public void setMode() {
        switch (mode) {
            case 0:
                studyModeFactory = new NormalStudyMode(content);
                break;
            case 1:
                studyModeFactory = new RandomStudyMode(content);
                break;
            case 2:
                studyModeFactory = new NormalStudyMode(content);
                break;
            case 3:
                studyModeFactory = new TTSStudyMode(content);
                ttsLinearLayout.setVisibility(View.VISIBLE);
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
            String contentString = studyModeFactory.getContent(0);
            contentTextview.setText(contentString);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }


    public void onReadTextButton() {
        tts.speak(contentTextview.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
    }


    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.KOREA);
            // tts.setPitch(5); // set pitch level
            // tts.setSpeechRate(2); // set speech speed rate

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(getApplicationContext(), "TTS 기능을 실행할 수 없습니다.", Toast.LENGTH_SHORT).show();
                isTTSValid = false;
            } else {
                isTTSValid = true;
            }

        } else {
            Log.e("TTS", "Initilization Failed");
            Toast.makeText(getApplicationContext(), "TTS 기능을 실행할 수 없습니다.", Toast.LENGTH_SHORT).show();
            isTTSValid = false;
        }
        setPlayButtonColor();
    }

    public void setPlayButtonColor() {
        if (isTTSValid) {
            ttsPlayButton.setTextColor(getResources().getColor(R.color.black));
            ttsStopButton.setTextColor(getResources().getColor(R.color.black));
        } else {
            ttsPlayButton.setTextColor(getResources().getColor(R.color.light_gray));
            ttsStopButton.setTextColor(getResources().getColor(R.color.light_gray));
        }
        ttsPlayButton.setEnabled(isTTSValid);
        ttsStopButton.setEnabled((isTTSValid));
    }
}