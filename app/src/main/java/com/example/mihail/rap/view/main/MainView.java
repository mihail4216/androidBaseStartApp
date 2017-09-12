package com.example.mihail.rap.view.main;


import com.example.mihail.rap.base.ViewBase;
import com.example.mihail.rap.presenter.main.IMainPresenter;

public interface MainView extends ViewBase<IMainPresenter>{
    void setName(String first_name);
}
