package com.invis.kinopoisk.features.presenter;

import com.invis.kinopoisk.features.Entity.Film;

public interface KinopoiskView extends MvpView {
    void onFilmSelect(Film film);
}
