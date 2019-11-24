package com.invis.kinopoisk.features.presenter;

import com.invis.kinopoisk.features.domain.KinopoiskInteractor;
import com.invis.kinopoisk.features.presenter.list.FilmsListPresenter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KinopoiskPresenter extends MvpPresenter<KinopoiskView>{
    KinopoiskInteractor kinopoiskInteractor;
    FilmsListPresenter filmsListPresenter;

    public FilmsListPresenter getFilmsListPresenter(){
        return filmsListPresenter;
    }
}