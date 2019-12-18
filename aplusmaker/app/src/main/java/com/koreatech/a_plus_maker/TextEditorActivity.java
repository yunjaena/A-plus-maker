package com.koreatech.a_plus_maker;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TextEditorActivity extends ActivityBase implements View.OnClickListener {
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private EditText textEditorEditText;
    private Button titleTextButton;
    private Button contentTextButton;
    private Button backSlashButton;
    private Boolean isPermitted;
    private Handler handler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_editor);
        init();
    }

    public void init() {
        isPermitted = false;
        handler = new Handler();
        textEditorEditText = findViewById(R.id.text_editor_text_input_edittext);
        titleTextButton = findViewById(R.id.text_editor_title_input_button);
        contentTextButton = findViewById(R.id.text_editor_content_button);
        backSlashButton = findViewById(R.id.text_editor_back_slash_button);
        titleTextButton.setOnClickListener(this);
        contentTextButton.setOnClickListener(this);
        backSlashButton.setOnClickListener(this);
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(R.string.text_editor);
    }

    @Override
    public void onClick(View v) {
        String inputLetter = "";
        int currenCusorPosition = textEditorEditText.getSelectionStart();
        switch (v.getId()) {
            case R.id.text_editor_title_input_button:
                inputLetter = "{}\n";
                break;
            case R.id.text_editor_content_button:
                inputLetter = "[]\n";
                break;
            case R.id.text_editor_back_slash_button:
                inputLetter = "/";
                break;
        }
        textEditorEditText.getText().insert(currenCusorPosition, inputLetter);
        textEditorEditText.setSelection(currenCusorPosition + 1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.text_editor_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onClickedSaveButton();
        return super.onOptionsItemSelected(item);

    }

    public void onClickedSaveButton() {
        if (textEditorEditText.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "문자를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)
            requestRuntimePermission();
        else
            isPermitted = true;

        if (isPermitted) {
            showTextFileAlertDialog();
        }


    }

    public void showTextFileAlertDialog() {
        AlertDialog.Builder ad = new AlertDialog.Builder(TextEditorActivity.this);
        ad.setTitle("제목");
        ad.setMessage("제목을 입력해주세요");
        final FrameLayout container = new FrameLayout(TextEditorActivity.this);
        final EditText et = new EditText(TextEditorActivity.this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = 50;
        params.rightMargin = 50;
        et.setLayoutParams(params);
        container.addView(et);
        ad.setView(container);
        ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String title = et.getText().toString();
                if (!title.trim().isEmpty()) {
                    saveText(title);
                    dialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), "제목을 입력해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();     //닫기
            }
        });
        ad.show();
    }

    public void saveText(final String title) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(getApplicationContext(), "저장할 외부저장소가 없습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        showProgressDialog();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String text = textEditorEditText.getText().toString();
                    if (!text.trim().equals("")) {
                        File filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsoluteFile();
                        File file = new File(filePath, title + ".txt");
                        if (!file.exists()) {
                            file.createNewFile();
                        } else {
                            Toast.makeText(getApplicationContext(), "파일이 이미 존재 합니다.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Log.e("TextEditorActivity", "saveText: " + filePath);
                        FileWriter fileWritter = new FileWriter(file, false);
                        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
                        bufferWritter.write(text);
                        bufferWritter.close();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                hideProgressDialog();
                                Toast.makeText(getApplicationContext(), "다운로드 폴더에 저장되었습니다.", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            hideProgressDialog();
                            Toast.makeText(getApplicationContext(), "파일 저장에 실패했습니다.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        }).start();


    }

    private void requestRuntimePermission() {
        if (ContextCompat.checkSelfPermission(TextEditorActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(TextEditorActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            isPermitted = false;
        } else {
            isPermitted = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    isPermitted = true;
                    showTextFileAlertDialog();

                } else {
                    isPermitted = false;
                    Toast.makeText(getApplicationContext(), "저장하기 위해 권한이 필요합니다.", Toast.LENGTH_SHORT).show();

                }
                return;
            }
        }
    }
}
