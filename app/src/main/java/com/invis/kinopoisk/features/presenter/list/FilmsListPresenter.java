package com.invis.kinopoisk.features.presenter.list;

import com.invis.kinopoisk.features.Entity.ListFilm;
import com.invis.kinopoisk.features.domain.KinopoiskInteractor;
import com.invis.kinopoisk.features.presenter.MvpPresenter;
import com.invis.kinopoisk.network.Carry;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FilmsListPresenter extends MvpPresenter<FilmsListView> {

    KinopoiskInteractor kinopoiskInteractor;


    public void loadFilmList() {
        kinopoiskInteractor.loadFilmList(new Carry<ListFilm>() {
            @Override
            public void onSuccess(ListFilm result) {
                view.addFilmList(result.getFilms());
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }
}
