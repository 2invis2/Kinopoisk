package com.invis.kinopoisk.features.presenter.list;

import com.invis.kinopoisk.features.Entity.Film;
import com.invis.kinopoisk.features.presenter.MvpView;

import java.util.List;

public interface FilmsListView extends MvpView {

    void showFilmList(List<Film> filmList);

    void addFilmList(List<Film> filmList);

    void showError(String message);
}
