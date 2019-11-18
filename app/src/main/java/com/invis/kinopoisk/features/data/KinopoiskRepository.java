package com.invis.kinopoisk.features.data;

import com.invis.kinopoisk.features.Entity.Film;
import com.invis.kinopoisk.network.Carry;

import java.util.List;

public interface KinopoiskRepository {

    public void loadFilmList (Carry<List<Film>> carry);
}
