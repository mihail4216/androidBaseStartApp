package com.example.mihail.rap.presenter.main;


import android.util.Log;

import com.example.mihail.rap.activity.MainActivity;
import com.example.mihail.rap.base.BasePresenter;
import com.example.mihail.rap.router.main.MainRouter;
import com.example.mihail.rap.view.main.MainView;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.methods.VKApiUsers;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKList;

import static android.R.id.list;

public class MainPresenter extends BasePresenter<MainView, MainRouter> implements IMainPresenter {

    String TAG = "Tag";

    private final String[] myScope = {
            VKApiConst.HTTPS,
            VKApiConst.NAME_CASE
    };

    @Override
    public void login(MainActivity mainActivity) {
        if (!VKSdk.isLoggedIn())
            VKSdk.login(mainActivity, myScope);
//        final VKRequest request = VKApi.users().get();
//        request.executeWithListener(new VKRequest.VKRequestListener() {
//            @Override
//            public void onComplete(VKResponse response) {
//                super.onComplete(response);
//                VKApiUser user = (VKApiUser) response.parsedModel;
//                String first_name = user.first_name;
//                getView().setName(first_name);
//                Log.d(TAG, "onComplete: ");
////                return user;
//
//            }
//
//            @Override
//            public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
//                super.attemptFailed(request, attemptNumber, totalAttempts);
//                Log.d(TAG, "attemptFailed: ");
//            }
//
//            @Override
//            public void onError(VKError error) {
//                super.onError(error);
//                Log.d(TAG, "onError: " + error.toString());
//            }
//
//            @Override
//            public void onProgress(VKRequest.VKProgressType progressType, long bytesLoaded, long bytesTotal) {
//                super.onProgress(progressType, bytesLoaded, bytesTotal);
//                Log.d(TAG, "onProgress: ");
//            }
//
//
//        });
    }
}
