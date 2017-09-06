package com.example.mihail.rap;

import android.app.Application;
import android.content.Context;

/**
 * Created by mihail on 06.09.17.
 */

public class AppStart extends Application{

    private static boolean isCoreInit = false;

    public static void initCore(Context context){
      isCoreInit = true;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initCore(this);
    }
}
