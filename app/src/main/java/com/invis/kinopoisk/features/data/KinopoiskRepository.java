package com.invis.kinopoisk.features.data;

import com.invis.kinopoisk.features.Entity.ListFilm;
import com.invis.kinopoisk.network.Carry;

public interface KinopoiskRepository {

    public void loadFilmList (Carry<ListFilm> carry);
}
