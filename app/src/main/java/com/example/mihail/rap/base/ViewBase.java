package com.example.mihail.rap.base;

import java.io.Serializable;

/**
 * Created by mihail on 06.09.17.
 */

public interface ViewBase<PRESENTER extends Presenter> extends Serializable {

    PRESENTER getPresenter();

    void onBind();

    void onInitListener();

    void showMessage(String text,boolean isLong);


}
