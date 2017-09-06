package com.example.mihail.rap.base;

import android.support.annotation.Nullable;

/**
 * Created by mihail on 06.09.17.
 */

public class BasePresenter<VIEW extends ViewBase,ROUTER extends Router> implements Presenter<VIEW,ROUTER> {

    private VIEW view;
    private ROUTER router;

    @Nullable
    @Override
    public VIEW getView() {
        return view;
    }

    @Nullable
    @Override
    public ROUTER getRouter() {
        return router;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onTakeView(VIEW view, ROUTER router) {
        this.view = view;
        this.router = router;

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        reset();

    }

    @Override
    public void onSerializable() {
        reset();

    }

    private void reset(){
        this.router = null;
        this.view = null;
    }
}
