package com.invis.kinopoisk.features.data;

import com.invis.kinopoisk.features.Entity.ListFilm;

import retrofit2.Call;
import retrofit2.http.GET;

public interface KinopoiskAPI {

    @GET("films.json")
    Call<ListFilm> getFilmList();

}
