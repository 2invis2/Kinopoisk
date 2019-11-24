package com.invis.kinopoisk.features.presenter.list;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invis.kinopoisk.R;
import com.invis.kinopoisk.features.Entity.Film;

import java.util.List;

public class FilmsListFragment extends Fragment implements FilmsListView{

    private final int SPAN_COUNT = 2;
    private RecyclerView recyclerView;
    private FilmAdapter adapter;
    private FilmsListPresenter filmsListPresenter;
    private View content;

    public void setPresenter(FilmsListPresenter presenter){
        filmsListPresenter = presenter;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        content = inflater.inflate(R.layout.list_films_layout, container,false);

        recyclerView = (RecyclerView) content.findViewById(R.id.list_recycler);
        adapter = new FilmAdapter(getContext(), new FilmAdapter.SelectFilmListener() {
            @Override
            public void onFilmSelect(Film film) {
               // presenter.onFilmSelected(film);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), SPAN_COUNT));

        return content;

    }

    protected FilmsListView getMvpView(){
        return this;
    }

    @Override
    public void onStart(){
        super.onStart();
        filmsListPresenter.attachView(getMvpView());

        filmsListPresenter.loadFilmList();
    }

    @Override
    public void onStop() {
        super.onStop();
        filmsListPresenter.detachView();
    }

    @Override
    public void showFilmList(List<Film> filmList) {

    }

    @Override
    public void addFilmList(List<Film> filmList) {
        adapter.addFilms(filmList);
    }

    @Override
    public void showError(String message) {
        Snackbar.make(content, message, Snackbar.LENGTH_SHORT).show();
    }
}
