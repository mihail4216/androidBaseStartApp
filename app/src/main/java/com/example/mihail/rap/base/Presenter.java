package com.example.mihail.rap.base;

import android.support.annotation.Nullable;

import java.io.Serializable;



public interface Presenter<VIEW extends ViewBase,ROUTER extends Router>extends Serializable {

    @Nullable
    VIEW getView();

    @Nullable
    ROUTER getRouter();

    void onResume();

    void onTakeView(VIEW view,ROUTER router);

    void onPause();

    void onDestroy();

    void onSerializable();

}
