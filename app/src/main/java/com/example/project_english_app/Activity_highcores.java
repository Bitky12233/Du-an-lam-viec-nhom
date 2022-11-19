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
    TextView Txt1;
    int HighScore;
    private Button  btnQzBack;

    @Override
    protected void onPostCreate( Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.layout_quiz_highcores);
        Txt1 = (TextView)findViewById(R.id.TxtHighscore);
        LoadHighScore();
        Txt1.setText(""+ HighScore);

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
        }
    }
    public void gobackActivity_quiz_home() {
        Intent intent = new Intent(this, Activity_quiz_home.class);
        startActivity(intent);
    }



}
