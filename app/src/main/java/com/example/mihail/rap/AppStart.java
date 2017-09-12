package com.example.mihail.rap;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;


public class AppStart extends Application{

    private static boolean isCoreInit = false;

    VKAccessTokenTracker accessTokenTracker = new VKAccessTokenTracker() {
        @Override
        public void onVKAccessTokenChanged(@Nullable VKAccessToken oldToken, @Nullable VKAccessToken newToken) {
            if(newToken == null){
            }
        }
    };

    public static void initCore(Context context){
        VKSdk.initialize(context);
        isCoreInit = true;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        accessTokenTracker.startTracking();
        initCore(this);
    }
}
