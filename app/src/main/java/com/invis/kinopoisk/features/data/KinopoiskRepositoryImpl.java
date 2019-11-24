package com.invis.kinopoisk.features.data;

import com.invis.kinopoisk.features.Entity.ListFilm;
import com.invis.kinopoisk.network.Carry;
import com.invis.kinopoisk.network.DefaultCallback;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KinopoiskRepositoryImpl implements KinopoiskRepository{
    KinopoiskAPI kinopoiskAPI;

    @Override
    public void loadFilmList(Carry<ListFilm> carry) {
        kinopoiskAPI.getFilmList().enqueue(new DefaultCallback<>(carry));
    }
}
