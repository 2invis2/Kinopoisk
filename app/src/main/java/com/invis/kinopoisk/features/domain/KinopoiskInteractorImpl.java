package com.invis.kinopoisk.features.domain;

import com.invis.kinopoisk.features.Entity.ListFilm;
import com.invis.kinopoisk.features.data.KinopoiskRepository;
import com.invis.kinopoisk.network.Carry;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KinopoiskInteractorImpl implements KinopoiskInteractor{

    KinopoiskRepository kinopoiskRepository;

    @Override
    public void loadFilmList(Carry<ListFilm> carry) {
        kinopoiskRepository.loadFilmList(carry);
    }
}
