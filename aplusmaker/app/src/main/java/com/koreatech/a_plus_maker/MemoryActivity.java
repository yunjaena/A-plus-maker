package com.koreatech.a_plus_maker;

import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Gravity;
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

import com.koreatech.a_plus_maker.studymode.BlinkStudyMode;
import com.koreatech.a_plus_maker.studymode.NormalStudyMode;
import com.koreatech.a_plus_maker.studymode.RandomStudyMode;
import com.koreatech.a_plus_maker.studymode.StudyModeFactory;
import com.koreatech.a_plus_maker.studymode.TTSStudyMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class MemoryActivity extends ActivityBase implements TextToSpeech.OnInitListener {
    private LinearLayout ttsLinearLayout;
    private LinearLayout blinkLinearLayout;
    private Spinner levelSpinner;
    private ImageView saveImageview;
    private TextView contentTextview;
    private StudyModeFactory studyModeFactory;
    private boolean isSaved;
    private String fileName;
    private String content;
    private int mode;
    private boolean isTTSValid;
    private Button ttsPlayButton;
    private Button ttsStopButton;
    private Button blinkPlayButton;
    private Button blinkStopButton;
    private TextToSpeech tts;
    private int currentBlinkLetterIndex = 0;
    private ArrayList<String> blinkLetters;
    private boolean isLettershow;
    private boolean isBlinkModeStart;
    private boolean isFileSaved;
    private FileList fileList;
    private Handler updateUIHandler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        init();
    }

    public void init() {
        tts = new TextToSpeech(this, this);
        blinkLetters = new ArrayList<>();
        isSaved = false;
        isTTSValid = false;
        isBlinkModeStart = false;
        isLettershow = false;
        updateUIHandler = new Handler();
        fileList = SaveFileSharedPrefernce.getInstance().loadFileList();
        getData();
        initView();
        setMode();
        checkFileIsSaved();

    }

    public void checkFileIsSaved() {
        if (fileList.isFileSaved(fileName)) {
            saveImageview.setImageResource(R.drawable.ic_flag_checked);
            isFileSaved = true;
        } else {
            saveImageview.setImageResource(R.drawable.ic_flag_uncheck);
            isFileSaved = false;
        }
    }

    private void initView() {
        levelSpinner = findViewById(R.id.memory_level_spinner);
        saveImageview = findViewById(R.id.memory_save_imageview);
        contentTextview = findViewById(R.id.memory_content_textview);
        blinkLinearLayout = findViewById(R.id.memory_blink_mode_linear_layout);
        saveImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedSaveButton();
            }
        });
        ttsPlayButton = findViewById(R.id.memory_play_tts_button);
        ttsStopButton = findViewById(R.id.memory_stop_tts_button);
        blinkStopButton = findViewById(R.id.memory_stop_blink_button);
        blinkPlayButton = findViewById(R.id.memory_play_blink_button);
        ttsLinearLayout = findViewById(R.id.memory_tts_mode_linear_layout);
        ttsStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
            }
        });
        ttsLinearLayout.setVisibility(View.GONE);
        blinkLinearLayout.setVisibility(View.GONE);
        ttsPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReadTextButton();
            }
        });
        ttsPlayButton.setEnabled(false);
        ttsStopButton.setEnabled((false));

        blinkPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBlinkMode();
            }
        });

        blinkStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopBlinkMode();
            }
        });
        setPlayButtonColor();
    }

    public void stopPlaying() {
        tts.stop();
    }


    public void setMode() {
        int modeNameResouceID = -1;
        switch (mode) {
            case 0:
                studyModeFactory = new NormalStudyMode(content);
                modeNameResouceID = R.string.normal_mode;
                break;
            case 1:
                studyModeFactory = new RandomStudyMode(content);
                modeNameResouceID = R.string.random_mode;
                break;
            case 2:
                studyModeFactory = new BlinkStudyMode(content);
                blinkLetters.addAll(Arrays.asList(studyModeFactory.getContent(0).split("/")));
                blinkLinearLayout.setVisibility(View.VISIBLE);
                contentTextview.setGravity(Gravity.CENTER);
                contentTextview.setText(R.string.please_play_start);
                contentTextview.setTextSize(35);
                modeNameResouceID = R.string.blink_mode;
                break;
            case 3:
                studyModeFactory = new TTSStudyMode(content);
                ttsLinearLayout.setVisibility(View.VISIBLE);
                contentTextview.setText(studyModeFactory.getContent(0));
                modeNameResouceID = R.string.tts_mode;
                break;
        }
        setAppbarText(modeNameResouceID);
        setSpinner();

    }

    public void setAppbarText(int id) {
        if (getSupportActionBar() != null && id != -1) {
            getSupportActionBar().setTitle(id);
        }
    }

    public void clickedSaveButton() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (isFileSaved) {
                    fileList.deleteFile(fileName);
                } else {
                    fileList.addFile(fileName, content);
                }
                SaveFileSharedPrefernce.getInstance().saveFileList(fileList);
                fileList = SaveFileSharedPrefernce.getInstance().loadFileList();
                updateUIHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        checkFileIsSaved();
                    }
                });
            }
        }).start();
        
       
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
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                showProgressDialog();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String contentString = studyModeFactory.getContent(position + 1);
                        updateUIHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                contentTextview.setText(contentString);
                                hideProgressDialog();
                            }
                        });
                    }
                }).start();
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
        fileName = getIntent().getStringExtra("FILE_NAME");
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
        int dividerLimit = 3900;
        String textForReading = contentTextview.getText().toString();
        if (textForReading.length() >= dividerLimit) {
            int textLength = textForReading.length();
            ArrayList<String> texts = new ArrayList<String>();
            int count = textLength / dividerLimit + ((textLength % dividerLimit == 0) ? 0 : 1);
            int start = 0;
            int end = textForReading.indexOf(" ", dividerLimit);
            for (int i = 1; i <= count; i++) {
                texts.add(textForReading.substring(start, end));
                start = end;
                if ((start + dividerLimit) < textLength) {
                    end = textForReading.indexOf(" ", start + dividerLimit);
                } else {
                    end = textLength;
                }
            }
            for (int i = 0; i < texts.size(); i++) {
                tts.speak(texts.get(i), TextToSpeech.QUEUE_ADD, null, null);
            }
        } else {
            tts.speak(textForReading, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }


    @Override
    public void onInit(int status) {
        showProgressDialog();
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
        hideProgressDialog();
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

    public void startBlinkMode() {
        isBlinkModeStart = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isBlinkModeStart) {
                    contentTextview.post(new Runnable() {
                        @Override
                        public void run() {
                            if (!isLettershow) {
                                contentTextview.setVisibility(View.INVISIBLE);
                                contentTextview.setText(blinkLetters.get(currentBlinkLetterIndex));
                                isLettershow = true;
                            } else {
                                contentTextview.setVisibility(View.VISIBLE);
                                isLettershow = false;
                                if (blinkLetters.size() <= currentBlinkLetterIndex + 1)
                                    currentBlinkLetterIndex = 0;
                                else
                                    currentBlinkLetterIndex++;
                            }
                        }
                    });
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.e("MemoryActivity", "run: " + currentBlinkLetterIndex);
                }
            }
        }).start();
    }

    public void stopBlinkMode() {
        isBlinkModeStart = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isBlinkModeStart = false;
    }
}