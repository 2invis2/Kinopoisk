package com.invis.kinopoisk.features.domain;

import com.invis.kinopoisk.features.Entity.Film;
import com.invis.kinopoisk.network.Carry;

import java.util.List;

public interface KinopoiskInteractor {

    public void loadFilmList (Carry<List<Film>> carry);
}
