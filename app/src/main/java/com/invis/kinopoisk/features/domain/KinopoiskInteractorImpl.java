package com.invis.kinopoisk.features.domain;

import com.invis.kinopoisk.features.Entity.Film;
import com.invis.kinopoisk.features.data.KinopoiskRepository;
import com.invis.kinopoisk.network.Carry;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KinopoiskInteractorImpl implements KinopoiskInteractor{

    KinopoiskRepository kinopoiskRepository;

    @Override
    public void loadFilmList(Carry<List<Film>> carry) {
        kinopoiskRepository.loadFilmList(carry);
    }
}
