package com.invis.kinopoisk.network;

public interface Carry<T> {

    void onSuccess(T result);

    void onFailure(Throwable throwable);

}
