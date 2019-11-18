package com.invis.kinopoisk.features.presenter;

import android.os.Bundle;

import com.invis.kinopoisk.R;

public class KinopoiskActivity extends BaseActivity{

    @Override
    protected <T extends MvpView> MvpPresenter<T> getPresenter() {
        return null;
    }

    @Override
    protected <T extends MvpView> T getMvpView() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kinopoisk);
    }
}