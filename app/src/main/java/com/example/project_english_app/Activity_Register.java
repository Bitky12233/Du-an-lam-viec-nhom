package com.example.project_english_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Activity_Register extends AppCompatActivity {
    private Button btnLogin;
    FirebaseAuth firebase_Auth;
    EditText Res_Email,Res_Pass;
    Button Btn_Res;
    String asd;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        firebase_Auth =FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        btnLogin = (Button) findViewById(R.id.loginButton_home);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity_LogIn();
            }
        });

        AnhXa();
        Btn_Res.setOnClickListener(new View.OnClickListener() {
            String email = Res_Email.getText().toString();
            String pass = Res_Pass.getText().toString();
            @Override
            public void onClick(View view) {
                firebase_Auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(Activity_Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isComplete()) {
                            Toast.makeText(Activity_Register.this,"Register success !",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Activity_Register.this, Activity_LogIn.class);
                            startActivity(intent);
                            finish();

                        }
                        else
                        {
                            if(TextUtils.isEmpty(email) || !email.contains("@") )
                            {
                                Toast.makeText(Activity_Register.this,"Email is not valid",Toast.LENGTH_LONG).show();
                            }
                            if(TextUtils.isEmpty(pass) || pass.length()<6)
                            {
                                Toast.makeText(Activity_Register.this,"Password is not valid",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
            }
        });

    }
    public void AnhXa(){
        Res_Email = (EditText) findViewById(R.id.emailRegis);//trong'
        Res_Pass = (EditText) findViewById(R.id.passwordRegis);//trong'
        Btn_Res = (Button)findViewById(R.id.registerButton);//trong'

    }

    public void openActivity_LogIn() {
        Intent intent = new Intent(this, Activity_LogIn.class);
        startActivity(intent);
    }
}
