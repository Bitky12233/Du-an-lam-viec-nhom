package com.example.project_english_app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_quiz_chooseQuestion extends Activity {
    private Button btnQzStart5,btnQzStart6,btnQzStart7,btnQzStart8,btnQzStart9,btnQzStart10;

    public int cauhoi;
    EditText N1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_choosequestion);
//        String Cauhoi = String.valueOf((EditText) findViewById(R.id.choosequestion));


        btnQzStart5 = (Button) findViewById(R.id.btn_SL5);
        btnQzStart5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_quiz_chooseQuestion.this, Activity_quiz_play.class);
                int A = 5 ;
                Bundle bundle = new Bundle();
                bundle.putInt("SL1", A);
                bundle.putInt("K", 0);
                bundle.putInt("H", 0);
                bundle.putInt("C", 0);
                bundle.putInt("B", 0);
                bundle.putInt("D", 0);
                bundle.putInt("Z", 0);
                intent.putExtra("MyPackage1", bundle);
                startActivity(intent);
                    finish();
//                if (A == 5) {
//
//                    startActivity(intent);
//                    finish();
//                } else if (A == 6) {
//                    startActivity(intent);
//                    finish();
//                } else if (A == 7) {
//                    startActivity(intent);
//                    finish();
//                } else if (A == 8) {
//                    startActivity(intent);
//                    finish();
//                } else if (A == 9) {
//                    startActivity(intent);
//                    finish();
////
//                } else if (A == 10) {
//                    startActivity(intent);
//                    finish();
////
//                }
//                switch (A) {
//                    case 5:
//                        openActivity_quiz_play();
//                        break;
//                    case 6:
//                        openActivity_quiz_play();
//                        break;
//                    case 7:
//                        openActivity_quiz_play();
//                        break;
//                    case 8:
//                        openActivity_quiz_play();
//                        break;
//                    case 9:
//                        openActivity_quiz_play();
//                        break;
//                    case 10:
//                        openActivity_quiz_play();
//                        break;
//
//                }
            }

        });
        btnQzStart6 = (Button) findViewById(R.id.btn_SL6);
        btnQzStart6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_quiz_chooseQuestion.this, Activity_quiz_play.class);
                int A = 6;
                Bundle bundle = new Bundle();
                bundle.putInt("SL1", A);
                bundle.putInt("K", 0);
                bundle.putInt("H", 0);
                bundle.putInt("C", 0);
                bundle.putInt("B", 0);
                bundle.putInt("D", 0);
                bundle.putInt("Z", 0);
                intent.putExtra("MyPackage1", bundle);
                startActivity(intent);
                finish();
            }
    });
        btnQzStart6 = (Button) findViewById(R.id.btn_SL6);
        btnQzStart6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_quiz_chooseQuestion.this, Activity_quiz_play.class);
                int A = 6;
                Bundle bundle = new Bundle();
                bundle.putInt("SL1", A);
                bundle.putInt("K", 0);
                bundle.putInt("H", 0);
                bundle.putInt("C", 0);
                bundle.putInt("B", 0);
                bundle.putInt("D", 0);
                bundle.putInt("Z", 0);
                intent.putExtra("MyPackage1", bundle);
                startActivity(intent);
                finish();
            }
        });
        btnQzStart7 = (Button) findViewById(R.id.btn_SL7);
        btnQzStart7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_quiz_chooseQuestion.this, Activity_quiz_play.class);
                int A = 7;
                Bundle bundle = new Bundle();
                bundle.putInt("SL1", A);
                bundle.putInt("K", 0);
                bundle.putInt("H", 0);
                bundle.putInt("C", 0);
                bundle.putInt("B", 0);
                bundle.putInt("D", 0);
                bundle.putInt("Z", 0);
                intent.putExtra("MyPackage1", bundle);
                startActivity(intent);
                finish();
            }
        });
        btnQzStart8 = (Button) findViewById(R.id.btn_SL8);
        btnQzStart8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_quiz_chooseQuestion.this, Activity_quiz_play.class);
                int A = 8;
                Bundle bundle = new Bundle();
                bundle.putInt("SL1", A);
                bundle.putInt("K", 0);
                bundle.putInt("H", 0);
                bundle.putInt("C", 0);
                bundle.putInt("B", 0);
                bundle.putInt("D", 0);
                bundle.putInt("Z", 0);
                intent.putExtra("MyPackage1", bundle);
                startActivity(intent);
                finish();
            }
        });
        btnQzStart9 = (Button) findViewById(R.id.btn_SL9);
        btnQzStart9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_quiz_chooseQuestion.this, Activity_quiz_play.class);
                int A = 9;
                Bundle bundle = new Bundle();
                bundle.putInt("SL1", A);
                bundle.putInt("K", 0);
                bundle.putInt("H", 0);
                bundle.putInt("C", 0);
                bundle.putInt("B", 0);
                bundle.putInt("D", 0);
                bundle.putInt("Z", 0);
                intent.putExtra("MyPackage1", bundle);
                startActivity(intent);
                finish();
            }
        });

        btnQzStart10 = (Button) findViewById(R.id.btn_SL10);
        btnQzStart10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_quiz_chooseQuestion.this, Activity_quiz_play.class);
                int A = 10;
                Bundle bundle = new Bundle();
                bundle.putInt("SL1", A);
                bundle.putInt("K", 0);
                bundle.putInt("H", 0);
                bundle.putInt("C", 0);
                bundle.putInt("B", 0);
                bundle.putInt("D", 0);
                bundle.putInt("Z", 0);
                intent.putExtra("MyPackage1", bundle);
                startActivity(intent);
                finish();
            }
        });
//    public void openActivity_quiz_play() {
//        Intent intent = new Intent(this, Activity_quiz_play.class);
//        startActivity(intent);
//    }
}}