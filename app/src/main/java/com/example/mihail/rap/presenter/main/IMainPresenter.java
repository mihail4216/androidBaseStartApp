package com.example.mihail.rap.presenter.main;


import com.example.mihail.rap.activity.MainActivity;
import com.example.mihail.rap.base.Presenter;
import com.example.mihail.rap.router.main.MainRouter;
import com.example.mihail.rap.view.main.MainView;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKList;

public interface IMainPresenter extends Presenter<MainView,MainRouter> {
    void login(MainActivity mainActivity);
}
