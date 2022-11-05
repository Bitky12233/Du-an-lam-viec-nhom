package com.example.project_english_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_Result extends Activity {
    Button BT;
    TextView KQ;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quiz_result);
//        KQ = (TextView) findViewById(R.id.TxtKQ);
//        BT = (Button)findViewById(R.id.BtnBack);
        Intent callerIntent=getIntent();
        Bundle packageFromCaller= ((Intent) callerIntent).getBundleExtra("MyPackage");
        KQ.setText(packageFromCaller.getInt("KQ")+"/"+ packageFromCaller.getInt("Socau"));
        BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
