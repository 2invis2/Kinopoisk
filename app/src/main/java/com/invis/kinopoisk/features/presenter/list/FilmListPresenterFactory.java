package com.invis.kinopoisk.features.presenter.list;

import android.content.Context;

import com.invis.kinopoisk.App;
import com.invis.kinopoisk.features.data.KinopoiskAPI;
import com.invis.kinopoisk.features.data.KinopoiskRepository;
import com.invis.kinopoisk.features.data.KinopoiskRepositoryImpl;
import com.invis.kinopoisk.features.domain.KinopoiskInteractor;
import com.invis.kinopoisk.features.domain.KinopoiskInteractorImpl;

final class FilmListPresenterFactory {

    static FilmsListPresenter createPresenter(Context context) {
        final KinopoiskAPI api = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(KinopoiskAPI.class);

        final KinopoiskRepository repository = new KinopoiskRepositoryImpl(api);
        final KinopoiskInteractor interactor = new KinopoiskInteractorImpl(repository);

        return new FilmsListPresenter(interactor);
    }
}
