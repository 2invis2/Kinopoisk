package com.invis.kinopoisk.features.presenter;

public class MvpPresenter<View extends MvpView>  {

    protected View view;

    public void attachView(View view) {
        this.view = view;
    }

    public void detachView() {
        view = null;
    }

    protected void onViewReady() {
    }
}
