package com.invis.kinopoisk.features.data;

import com.invis.kinopoisk.features.Entity.Film;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface KinopoiskAPI {

    @GET("films.json")
    Call<List<Film>> getFilmList();

}
