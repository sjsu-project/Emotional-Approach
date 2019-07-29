package com.example.android;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;


public class LoginActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener{

    // 구글로그인 result 상수
    private static final int RC_SIGN_IN = 900;

    // 구글api클라이언트
    private GoogleSignInClient googleSignInClient;

    // 파이어베이스 인증 객체 생성
    private FirebaseAuth firebaseAuth;

    // 구글  로그인 버튼
    private SignInButton buttonGoogle;

    private Button temp_button;
    private static final String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Log.d(TAG,"start");
        // 파이어베이스 인증 객체 선언
        firebaseAuth = FirebaseAuth.getInstance();

        buttonGoogle = findViewById(R.id.Google_Login);

        // Google 로그인을 앱에 통합
        // GoogleSignInOptions 개체를 구성할 때 requestIdToken을 호출
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);



        buttonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
                Log.d(TAG,"Login Button Click");
                //finish();

            }
        });

        temp_button = findViewById(R.id.temp_button);
        temp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent success = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(success);
                finish();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 구글로그인 버튼 응답
//        String a = toString(requestCode);
//        Log.d(TAG,requestCode);
        Log.d(TAG,"onActivityResult");

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                // 구글 로그인 성공
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
                String name = account.getDisplayName();
                String email = account.getEmail();
                String img_url = account.getPhotoUrl().toString();
                Log.d(TAG,name);
                Log.d(TAG,email);

//                Intent success = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(success);
//
//                finish();

            } catch (ApiException e) {

                Log.d(TAG,"failed");
                Log.d(TAG, String.valueOf(e));
                Log.d(TAG,"failed");
            }
        }
    }

    // 사용자가 정상적으로 로그인한 후에 GoogleSignInAccount 개체에서 ID 토큰을 가져와서
// Firebase 사용자 인증 정보로 교환하고 Firebase 사용자 인증 정보를 사용해 Firebase에 인증합니다.
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        Log.d(TAG,"firebaseAuthWithGoogle");
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 로그인 성공
                            Log.d(TAG, "Login success");
                            //Toast.makeText(RegisterActivity.this, R.string.success_login, Toast.LENGTH_SHORT).show();
//                            Intent success = new Intent(LoginActivity.this, MainActivity.class);
//                            startActivity(success);

                            finish();
                        } else {
                            // 로그인 실패
                            Log.d(TAG, "Login Failed");
                            //Toast.makeText(RegisterActivity.this, R.string.failed_login, Toast.LENGTH_SHORT).show();
                            Intent success = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(success);
                        }


                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Log.d(TAG,"onconnection failed");
    }

}