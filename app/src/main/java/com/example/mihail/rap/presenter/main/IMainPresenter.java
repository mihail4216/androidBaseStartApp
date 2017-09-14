package com.example.mihail.rap.presenter.main;


import android.content.Intent;

import com.example.mihail.rap.activity.MainActivity;
import com.example.mihail.rap.base.Presenter;
import com.example.mihail.rap.router.main.MainRouter;
import com.example.mihail.rap.view.main.MainView;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKList;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public interface IMainPresenter extends Presenter<MainView,MainRouter> {

    void login(MainActivity mainActivity);

    void getPersonalData(VKResponse response);

    void isPasswordEdit(int requestCode, int resultCode, Intent data);

    ArrayList getAllFriends(VKResponse response) throws JSONException;
}
