package com.example.project_english_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 2500;
    ImageView imageView;
    TextView textView1;
    Animation top, bottom;
    SignInButton signin;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail11111
//                .build();
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        imageView = findViewById(R.id.img_showApp);
        textView1 = findViewById(R.id.textView_showApp);
//        signin = findViewById(R.id.sign_in_button);
//        signin.setOnClickListener(view -> {
//            switch (view.getId()) {
//                case R.id.sign_in_button:
//                    signIn();
//                    break;
//                // ...
//            }
//        });

        top = AnimationUtils.loadAnimation(this, R.anim.top);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom);

        imageView.setAnimation(top);
        imageView.setAnimation(bottom);

         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 Intent intent = new Intent(MainActivity.this, LogIn.class);

                 startActivity(intent);
                 finish();
             }
         },SPLASH_SCREEN);

    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    public void AnhXa()
    {
        imageView = findViewById(R.id.img_showApp);
        textView1 = findViewById(R.id.textView_showApp);
        signin = findViewById(R.id.sign_in_button);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Intent intent = new Intent(MainActivity.this,Second.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("error", "signInResult:failed code=" + e.getStatusCode());

        }
    }


}