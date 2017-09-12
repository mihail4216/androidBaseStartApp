package com.example.mihail.rap.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public abstract class BaseFragment<PRESENTER extends Presenter> extends Fragment implements ViewBase<PRESENTER>,Router{

    private final String PRESENTER_TAG = "PRESENTER_CACHE_NUMBER";

    private PRESENTER presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.getSerializable(PRESENTER_TAG) != null)
            presenter = (PRESENTER) savedInstanceState.getSerializable(PRESENTER_TAG);
        if (presenter == null)
            presenter = createPresenter();
    }

    protected abstract PRESENTER createPresenter();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSerializable();
        outState.putSerializable(PRESENTER_TAG, presenter);
    }

    @Override
    public void navigateToBack() {

    }

    @Override
    public PRESENTER getPresenter() {
        return presenter;
    }

    @Override
    public void onBind() {

    }

    @Override
    public void onInitListener() {

    }
    public abstract String getFragmentTag();

    @Override
    public void showMessage(String text, boolean isLong) {

    }
}
