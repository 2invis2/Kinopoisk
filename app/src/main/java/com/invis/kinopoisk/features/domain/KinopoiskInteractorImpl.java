package com.invis.kinopoisk.features.domain;

import com.invis.kinopoisk.features.data.KinopoiskRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KinopoiskInteractorImpl implements KinopoiskInteractor{
    KinopoiskRepository kinopoiskRepository;
}
