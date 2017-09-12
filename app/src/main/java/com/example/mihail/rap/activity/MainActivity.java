package com.example.mihail.rap.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.mihail.rap.R;
import com.example.mihail.rap.base.BaseActivity;
import com.example.mihail.rap.presenter.main.IMainPresenter;
import com.example.mihail.rap.presenter.main.MainPresenter;
import com.example.mihail.rap.router.main.MainRouter;
import com.example.mihail.rap.view.main.MainView;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKObject;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.methods.VKApiUsers;
import com.vk.sdk.api.model.VKApiAudio;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;
import com.vk.sdk.api.model.VKUsersArray;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends BaseActivity<IMainPresenter> implements MainView, MainRouter {

    private static final String TAG = "MainActivity";

    private TextView text;

    private final String[] myScope = {
            VKApiConst.HTTPS,
            VKApiConst.NAME_CASE,
            VKApiConst.CITY,
            VKApiConst.AGE_FROM,
            VKApiConst.MESSAGE
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getPresenter().login(this);
        if (!VKSdk.isLoggedIn())
            VKSdk.login(this, myScope);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                Log.d(TAG, "onResult: "+ res.userId);
            }

            @Override
            public void onError(VKError error) {

            }
        }))
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBind() {
        text = (TextView) findViewById(R.id.helloworld);
        final VKRequest request = VKApi.users().get(VKParameters.from(VKApiConst.CITY,VKApiConst.AGE_TO));
        final VKRequest request1 = VKApi.users().getSubscriptions();
        request1.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                JSONObject obj = response.json;
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
            }
        });
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                Log.d(TAG, "onComplete: ");
                try {
                    text.setText((CharSequence) ((VKList) response.parsedModel).get(0).fields.get("first_name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                Log.d(TAG, "onError: ");
            }
        });
    }

    @Override
    public void onInitListener() {

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
