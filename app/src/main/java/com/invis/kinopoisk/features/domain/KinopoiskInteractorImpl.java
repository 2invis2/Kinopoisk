package com.invis.kinopoisk.features.domain;

import com.invis.kinopoisk.features.Entity.Film;
import com.invis.kinopoisk.features.Entity.ListFilm;
import com.invis.kinopoisk.features.data.KinopoiskRepository;
import com.invis.kinopoisk.network.Carry;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KinopoiskInteractorImpl implements KinopoiskInteractor{

    KinopoiskRepository kinopoiskRepository;

    @Override
    public void loadFilmList(Carry<ListFilm> carry) {
        kinopoiskRepository.loadFilmList(carry);
    }

    @Override
    public List<String> getGeners(List<Film> filmList) {

        List<String> geners = new ArrayList<String>();

        for(int i = 0; i < filmList.size(); i++){
            for(int j = 0; j < filmList.get(i).getGenres().size(); j++){

                if( !(geners.contains(filmList.get(i).getGenres().get(j))) ){
                    geners.add(filmList.get(i).getGenres().get(j));
                }

            }
        }

        return geners;
    }
}
