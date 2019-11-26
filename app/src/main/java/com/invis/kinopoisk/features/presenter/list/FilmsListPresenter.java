package com.invis.kinopoisk.features.presenter.list;

import com.invis.kinopoisk.features.Entity.Film;
import com.invis.kinopoisk.features.Entity.ListFilm;
import com.invis.kinopoisk.features.domain.KinopoiskInteractor;
import com.invis.kinopoisk.features.presenter.KinopoiskView;
import com.invis.kinopoisk.features.presenter.MvpPresenter;
import com.invis.kinopoisk.network.Carry;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FilmsListPresenter extends MvpPresenter<FilmsListView> {

    KinopoiskInteractor kinopoiskInteractor;


    public void loadFilmList() {
        view.showProgress();
        kinopoiskInteractor.loadFilmList(new Carry<ListFilm>() {
            @Override
            public void onSuccess(ListFilm result) {
                view.addFilmList(result.getFilms());
                view.showGeners(result.getFilms());
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
                view.hideProgress();
            }
        });
    }

    public void onFilmSelected(Film film, KinopoiskView kinopoiskActivity) {
        kinopoiskActivity.onFilmSelect(film);
    }

    public List<String> getGeners(List<Film> filmList) {
        return kinopoiskInteractor.getGeners(filmList);
    }
}
