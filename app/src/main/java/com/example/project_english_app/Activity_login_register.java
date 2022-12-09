package com.example.project_english_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_login_register extends AppCompatActivity {
    private Button btnLogin, btnRegister;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login_register);

        btnLogin = (Button) findViewById(R.id.loginButton_home);
        btnRegister = (Button) findViewById(R.id.registerButton_home);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity_LogIn();
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity_Register();
            }
        });

    }

    public void openActivity_LogIn() {
        Intent intent = new Intent(this, Activity_LogIn.class);
        startActivity(intent);
    }

    public void openActivity_Register() {
        Intent intent = new Intent(this, Activity_Register.class);
        startActivity(intent);
    }

}