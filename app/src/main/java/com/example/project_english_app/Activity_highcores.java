package com.example.project_english_app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_highcores extends Activity {
    TextView Txt1,Txt2,Txt3,Txt4,Txt5;
    int HighScore;
    private Button  btnQzBack;
    int High = 0;
    int High2 =0;
    int High3 =0 ;
    int High4 =0 ;
    int High5 =0;

    @Override
    protected void onPostCreate( Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.layout_quiz_highcores);
        Txt1 = (TextView)findViewById(R.id.TxtHighscore);
        Txt2 = (TextView)findViewById(R.id.TxtHigh2);
        Txt3 = (TextView)findViewById(R.id.TxtHigh3);
        Txt4 = (TextView)findViewById(R.id.TxtHigh4);
        Txt5 = (TextView)findViewById(R.id.TxtHigh5);



        LoadHighScore();



        Txt1.setText(""+ HighScore);
        Txt2.setText(""+ High2);
        Txt3.setText(""+ High3);
        Txt4.setText(""+ High4);
        Txt5.setText(""+ High5);

        btnQzBack  = (Button) findViewById(R.id.btn_Back);
        btnQzBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ) {
                gobackActivity_quiz_home();
            }
            });
    }
    void LoadHighScore(){
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        if (sharedPreferences !=null){
            HighScore = sharedPreferences.getInt("H",0);
            High2 = sharedPreferences.getInt("C",0);
            High3 = sharedPreferences.getInt("B",0);
            High4 = sharedPreferences.getInt("D",0);
            High5 = sharedPreferences.getInt("Z",0);
        }
    }
    public void gobackActivity_quiz_home() {
        Intent intent = new Intent(this, Activity_quiz_home.class);
        startActivity(intent);
    }



}
