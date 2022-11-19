package com.example.project_english_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_quiz_home extends AppCompatActivity {
    private Button btnQzStart, btnQzHigSco, btnQzInstuc, btnQzBack;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quiz_home);

        btnQzStart = (Button) findViewById(R.id.btn_QuizStart);
        btnQzStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              openActivity_quiz_play();
            }
        });
        btnQzHigSco = (Button) findViewById(R.id.btn_QuizHighSco);
        btnQzHigSco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity_highcore();
            }
        });

        btnQzInstuc = (Button) findViewById(R.id.btn_QuizInstruc);
        btnQzInstuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity_quiz_intruction();
            }
        });

        btnQzBack  = (Button) findViewById(R.id.btn_QuizBack_HomeMenu);
        btnQzBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gobackActivity_menu();
            }
        });

    }

    public void openActivity_quiz_play() {
        Intent intent = new Intent(this, Activity_quiz_play.class);
        startActivity(intent);
    }

    public void openActivity_quiz_intruction() {
        Intent intent = new Intent(this, Activity_quiz_instruction.class);
        startActivity(intent);
    }

    public void gobackActivity_menu() {
        Intent intent = new Intent(this, Activity_menu.class);
        startActivity(intent);
    }
    public void openActivity_highcore(){
        Intent intent = new Intent(this,Activity_highcores.class);
        startActivity(intent);
    }

}