package com.example.mihail.rap.base;

import java.io.Serializable;


public interface ViewBase<PRESENTER extends Presenter> extends Serializable {

    PRESENTER getPresenter();

    void onBind();

    void onInitListener();

    void showMessage(String text,boolean isLong);


}
