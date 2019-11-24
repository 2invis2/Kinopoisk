package com.invis.kinopoisk.features.domain;

import com.invis.kinopoisk.features.Entity.ListFilm;
import com.invis.kinopoisk.network.Carry;

public interface KinopoiskInteractor {

    public void loadFilmList (Carry<ListFilm> carry);
}
