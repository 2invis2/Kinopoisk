package com.invis.kinopoisk.features.domain;

import com.invis.kinopoisk.features.Entity.Film;
import com.invis.kinopoisk.features.Entity.ListFilm;
import com.invis.kinopoisk.network.Carry;

import java.util.List;

public interface KinopoiskInteractor {

    void loadFilmList (Carry<ListFilm> carry);

    List<String> getGeners (List<Film> filmList);
}
