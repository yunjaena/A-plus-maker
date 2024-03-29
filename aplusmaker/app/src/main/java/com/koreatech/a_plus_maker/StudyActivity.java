package com.koreatech.a_plus_maker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyActivity extends ActivityBase {
    public static final String TAG = StudyActivity.class.getName();
    public static final String[] MODE = {"일반모드학습", "랜덤모드학습", "깜박이모드학습", "TTS 모드"};
    public static final int FILE_SELECT_CODE = 1;
    private TextView modeText;
    private boolean isFileLoaded;
    private String fileContent;
    private int selectMode;
    private boolean isModeSelected;
    private Button modeButton;
    private Button startButton;
    private Button explorerButton;
    private Handler fileLoadHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 액션바(타이틀바) 감추기
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        fileLoadHandler = new Handler();
        modeText = findViewById(R.id.study_mode);
        isFileLoaded = false;
        isModeSelected = false;
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.study_name);
        }

        modeButton = (Button) findViewById(R.id.study_mode);
        modeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMode();
            }
        });

        startButton = (Button) findViewById(R.id.study_start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStart();
            }
        });

        explorerButton = (Button) findViewById(R.id.explorer);
        explorerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });

        if (getIntent() != null) {
            if (getIntent().getStringExtra("TITLE") != null)
                explorerButton.setText(getIntent().getStringExtra("TITLE"));

            if (getIntent().getStringExtra("CONTENT") != null) {
                fileContent = (getIntent().getStringExtra("CONTENT"));
                isFileLoaded = true;
            }


        }
    }

    void showFileChooser() {
        Intent intent;
        intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("text/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }


    void showStart() {
        if (!isFileLoaded) {
            Toast.makeText(this, "파일을 선택해 주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isModeSelected) {
            Toast.makeText(this, "모드 선택해 주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, MemoryActivity.class);
        intent.putExtra("MEMORY_MODE", selectMode);
        intent.putExtra("FILE", fileContent);
        intent.putExtra("FILE_NAME", explorerButton.getText().toString());
        startActivity(intent);

    }

    public void showMode() {
        final List<String> listItems = new ArrayList<>(Arrays.asList(MODE));
        final CharSequence[] items = listItems.toArray(new String[listItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("학습 모드를 선택해주세요");

        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int pos) {
                String selectedText = items[pos].toString();
                modeText.setText(selectedText);
                selectMode = pos;
                isModeSelected = true;
                Toast.makeText(StudyActivity.this, selectedText, Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    private String readTextFile(Uri uri) {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(getContentResolver().openInputStream(uri)));
            String line = "";

            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        isFileLoaded = false;
        if (requestCode == FILE_SELECT_CODE) {
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    Toast.makeText(this, "파일을 못 읽어 왔습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                final Uri uri = data.getData();
                if (uri == null) {
                    Toast.makeText(this, "파일을 못 읽어 왔습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                showProgressDialog();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        fileContent = readTextFile(uri);
                        if (uri.getPath() != null) {
                            final String fileName = new File(uri.getPath()).getName().replace(".txt", "");
                            fileLoadHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    explorerButton.setText(fileName);
                                    hideProgressDialog();
                                }
                            });
                        }
                        if (!fileContent.isEmpty())
                            isFileLoaded = true;
                    }
                }).start();

            }
        }
    }
}
