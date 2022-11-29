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
    private Button btnQzStart;
    public  int cauhoi ;
    EditText N1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_choosequestion);
//        String Cauhoi = String.valueOf((EditText) findViewById(R.id.choosequestion));

        N1 = (EditText) findViewById(R.id.choosequestion);

        btnQzStart = (Button) findViewById(R.id.btn_StartChoose);
        btnQzStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_quiz_chooseQuestion.this, Activity_quiz_play.class);
                int A = Integer.parseInt(N1.getText().toString());
                Bundle bundle = new Bundle();
                bundle.putInt("SL1", A);
                intent.putExtra("MyPackage", bundle);
                if (A == 7) {

                    startActivity(intent);
                }
                 else if (A == 6) {
                startActivity(intent);
                } else if (A == 7) {
                startActivity(intent);
                } else if (A == 8) {
                startActivity(intent);
                } else if (A == 9) {
                startActivity(intent);
//
                } else if (A == 10) {
                startActivity(intent);
//
                }
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

    }
//    public void openActivity_quiz_play() {
//        Intent intent = new Intent(this, Activity_quiz_play.class);
//        startActivity(intent);
//    }
}