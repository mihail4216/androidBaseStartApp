package com.example.mihail.rap.friends;


import android.util.Log;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Friend{

    private static final String TAG = "MainFriends";

    private String full_name;
    private String last_name;
    private String first_name;
    private int id;



    public Friend(int id){
        this.id = id;
//        final VKRequest requestUser = VKApi.users().get(VKParameters.from(VKApiConst.USER_ID,""+id));
//        requestUser.executeWithListener(new VKRequest.VKRequestListener() {
//            @Override
//            public void onComplete(VKResponse response) {
//                super.onComplete(response);
//                try {
//                    last_name = (String) ((VKList) response.parsedModel).get(0).fields.get("last_name");
//                    first_name = (String) ((VKList) response.parsedModel).get(0).fields.get("first_name");
//                    full_name = first_name +" "+last_name;
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                Log.d(TAG, "onComplete: ");
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
//            public void onProgress(VKRequest.VKProgressType progressType, long bytesLoaded, long bytesTotal) {
//                super.onProgress(progressType, bytesLoaded, bytesTotal);
//                Log.d(TAG, "onProgress: ");
//            }
//
//            @Override
//            public void onError(VKError error) {
//                super.onError(error);
//                Log.d(TAG, "onError: ");
//            }
//        });
    }

}
