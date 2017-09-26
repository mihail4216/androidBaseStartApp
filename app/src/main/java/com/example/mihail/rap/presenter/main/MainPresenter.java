package com.example.mihail.rap.presenter.main;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.mihail.rap.activity.MainActivity;
import com.example.mihail.rap.base.BasePresenter;
import com.example.mihail.rap.friends.Friend;
import com.example.mihail.rap.router.main.MainRouter;
import com.example.mihail.rap.view.main.MainView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.Executor;

public class MainPresenter extends BasePresenter<MainView, MainRouter> implements IMainPresenter {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    String TAG = "MainPresenter";

    private final String[] myScopeLogin = {
        VKApiConst.VERSION,"5",

    };

    @Override
    public void login(MainActivity mainActivity) {
        if (!VKSdk.isLoggedIn())
            VKSdk.login(mainActivity, myScopeLogin);
    }

    @Override
    public void getPersonalData(VKResponse response) {
        VKList users = (VKList) response.parsedModel;
        VKApiUserFull user = (VKApiUserFull) users.get(0);
        Log.d(TAG, "onComplete: " + user.first_name);

    }

    @Override
    public void isPasswordEdit(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                Log.d(TAG, "onResult: " + res.userId);
            }

            @Override
            public void onError(VKError error) {

            }
        }));
    }

    @Override
    public ArrayList<Friend> getAllFriends(VKResponse response) throws JSONException {
        JSONArray listUsers = null;
        int countFriends = 0;
        ArrayList<Friend> listFriends = new ArrayList<>();
        try {
            countFriends = (int) response.json.getJSONObject("response").get("count");
            listUsers = response.json.getJSONObject("response").getJSONArray("items");
            for(int i=0;i<countFriends;i++) {
                int id = (int) listUsers.get(i);
                listFriends.add(new Friend(id));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listFriends;
    }

    @Override
    public void init() {
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid() + "  " + user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    @Override
    public void signUp(String email, String pass, final MainActivity mainActivity) {
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(mainActivity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) Toast.makeText(mainActivity,"Successful!!!",Toast.LENGTH_SHORT).show();
                else Toast.makeText(mainActivity,"Error!!!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void signIn(String email, String pass, final MainActivity mainActivity) {
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(mainActivity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) Toast.makeText(mainActivity,"Successful!!!",Toast.LENGTH_SHORT).show();
                else Toast.makeText(mainActivity,"Error!!!",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
