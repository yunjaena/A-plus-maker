package com.koreatehc.a_plus_maker;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class StudyActivity extends AppCompatActivity {

    public static final int FILE_SELECT_CODE = 1;
    TextView modeText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 액션바(타이틀바) 감추기
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        modeText =findViewById(R.id.study_mode);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.study_name);
        }

        Button modeButton = (Button)findViewById(R.id.study_mode);
        modeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_mode();
            }
        });

        Button startButton = (Button)findViewById(R.id.study_start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_start();
            }
        });

        Button explorerButton = (Button)findViewById(R.id.explorer);
        explorerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });
    }

    void showFileChooser() {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //intent.setType("*/*");      //all files
        intent.setType("text/*");   //XML file only
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }


    void show_start(){
        Toast.makeText(getApplicationContext(), "Button pressed!", Toast.LENGTH_SHORT).show();
    }

    void show_mode()
    {
        final List<String> ListItems = new ArrayList<>();
        ListItems.add("일반모드학습");
        ListItems.add("랜덤모드학습");
        ListItems.add("깜박이모드학습");
        ListItems.add("게임모드학습");
        final CharSequence[] items =  ListItems.toArray(new String[ ListItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("학습 모드를 선택해주세요");

        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int pos) {
                String selectedText = items[pos].toString();
                modeText.setText(selectedText);
                Toast.makeText(StudyActivity.this, selectedText, Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
}
