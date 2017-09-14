package com.example.mihail.rap.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mihail.rap.R;
import com.example.mihail.rap.base.BaseActivity;
import com.example.mihail.rap.presenter.main.IMainPresenter;
import com.example.mihail.rap.presenter.main.MainPresenter;
import com.example.mihail.rap.router.main.MainRouter;
import com.example.mihail.rap.view.main.MainView;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONException;

public class MainActivity extends BaseActivity<IMainPresenter> implements MainView, MainRouter {

    private static final String TAG = "MainActivity";

    private TextView text;

    private VKRequest requestMyUser, requestMyFriends;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPresenter().login(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getPresenter().isPasswordEdit(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onBind() {
        text = (TextView) findViewById(R.id.helloworld);
        requestMyUser = VKApi.users().get(VKParameters.from(VKApiConst.VERSION,"5"));
        requestMyFriends = VKApi.messages().get();
//        requestMyFriends = VKApi.messages().get(VKParameters.from(VKApiConst.COUNT,25));


    }


    @Override
    public void onInitListener() {
        requestMyFriends.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                try {
                    getPresenter().getAllFriends(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                    
                }
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                Log.d(TAG, "onError: " + error);
            }
        });
        requestMyUser.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                getPresenter().getPersonalData(response);
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
            }
        });
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void setName(String first_name) {
        text.setText(first_name);
    }
}
