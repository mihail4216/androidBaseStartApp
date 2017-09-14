package com.example.mihail.rap.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;




public abstract class BaseActivity<PRESENTER extends Presenter> extends AppCompatActivity implements ViewBase<PRESENTER>,Router {

    private PRESENTER presenter;

    private final String PRESENTER_TAG = "PRESENTER_CACHE_NUMBER";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null && savedInstanceState.getSerializable(PRESENTER_TAG) != null)
            presenter = (PRESENTER) savedInstanceState.getSerializable(PRESENTER_TAG);
        if(presenter == null)
            presenter = createPresenter();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (presenter == null)
            presenter = createPresenter();
        presenter.onSerializable();
        outState.putSerializable(PRESENTER_TAG,presenter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(presenter!=null)
            presenter.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        onBind();
        if(presenter != null)
            presenter.onTakeView(this,this);
        onInitListener();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if(presenter != null)
            presenter.onPause();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null)
            presenter.onDestroy();
    }

    protected abstract PRESENTER createPresenter();

    @Override
    public void navigateToBack() {
        finish();
    }

    @Override
    public PRESENTER getPresenter() {
        if(presenter == null)
            presenter = createPresenter();
        return presenter;
    }

    @Override
    public void showMessage(String text, boolean isLong) {

    }
}
