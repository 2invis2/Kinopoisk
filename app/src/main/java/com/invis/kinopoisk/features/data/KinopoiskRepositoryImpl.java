package com.invis.kinopoisk.features.data;

import com.invis.kinopoisk.features.Entity.Film;
import com.invis.kinopoisk.network.Carry;
import com.invis.kinopoisk.network.DefaultCallback;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KinopoiskRepositoryImpl implements KinopoiskRepository{
    KinopoiskAPI kinopoiskAPI;

    @Override
    public void loadFilmList(Carry<List<Film>> carry) {
        kinopoiskAPI.getFilmList().enqueue(new DefaultCallback<>(carry));
    }
}
