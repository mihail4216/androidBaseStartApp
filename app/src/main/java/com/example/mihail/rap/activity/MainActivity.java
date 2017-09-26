package com.example.mihail.rap.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mihail.rap.R;
import com.example.mihail.rap.base.BaseActivity;
import com.example.mihail.rap.presenter.main.IMainPresenter;
import com.example.mihail.rap.presenter.main.MainPresenter;
import com.example.mihail.rap.router.main.MainRouter;
import com.example.mihail.rap.view.main.MainView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

public class MainActivity extends BaseActivity<IMainPresenter> implements MainView, MainRouter {

    private static final String TAG = "MainActivity";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText emailTxt;
    private EditText passwordTxt;

    private Button btnLogin;
    private Button btnVk;
    private Button btnRegistration;

    private VKRequest requestMyUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getPresenter().login(this);
        getPresenter().init();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getPresenter().isPasswordEdit(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBind() {
        requestMyUser = VKApi.users().get(VKParameters.from(VKApiConst.VERSION, 5));
        emailTxt = (EditText) findViewById(R.id.emailText);
        passwordTxt = (EditText) findViewById(R.id.passwordText);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegistration = (Button) findViewById(R.id.btnSingUp);
        btnVk = (Button) findViewById(R.id.btnLoginWithVk);


    }


    @Override
    public void onInitListener() {

        requestMyUser.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
//                getPresenter().getPersonalData(response);
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
            }
        });
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getPresenter().signUp(emailTxt.getText().toString(),passwordTxt.getText().toString(),MainActivity.this);


            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().signIn(emailTxt.getText().toString(),passwordTxt.getText().toString(),MainActivity.this);

            }
        });
        btnVk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
//    private void signIn(String email, String pass) {
//        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) Toast.makeText(MainActivity.this,"Successful!!!",Toast.LENGTH_SHORT).show();
//                else Toast.makeText(MainActivity.this,"Error!!!",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    private void signUp(String email,String pass){
//
//        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) Toast.makeText(MainActivity.this,"Successful!!!",Toast.LENGTH_SHORT).show();
//                else Toast.makeText(MainActivity.this,"Error!!!",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void signUp() {

    }

    @Override
    public void login() {

    }

    @Override
    public void loginWithVk() {

    }

}


