package com.example.mihail.rap.presenter.main;


import android.content.Intent;
import android.util.Log;

import com.example.mihail.rap.activity.MainActivity;
import com.example.mihail.rap.base.BasePresenter;
import com.example.mihail.rap.friends.Friend;
import com.example.mihail.rap.router.main.MainRouter;
import com.example.mihail.rap.view.main.MainView;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter extends BasePresenter<MainView, MainRouter> implements IMainPresenter {

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
}
