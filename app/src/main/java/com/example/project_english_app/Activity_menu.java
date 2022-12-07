package com.example.project_english_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Activity_menu extends AppCompatActivity {
    private Button btnQuiz, btnCorrect, btnLogout;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu);

        btnLogout = (Button) findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gobackLogIn();
            }
        });

        btnQuiz = (Button) findViewById(R.id.btn_Quiz);
        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openActivity_quiz_home();
            }
        });

        btnCorrect = (Button) findViewById(R.id.btn_Correct);
        btnCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity_correct_home();
            }
        });
    }

    public void gobackLogIn() {
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }

    public void openActivity_quiz_home() {
        Intent intent = new Intent(this, Activity_quiz_home.class);
        startActivity(intent);
    }

    public void openActivity_correct_home() {
        Intent intent = new Intent(this, Activity_correct_home.class);
        startActivity(intent);
    }
}