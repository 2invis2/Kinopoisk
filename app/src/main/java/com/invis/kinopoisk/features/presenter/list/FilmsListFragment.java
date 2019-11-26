package com.invis.kinopoisk.features.presenter.list;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.invis.kinopoisk.R;
import com.invis.kinopoisk.features.Entity.Film;
import com.invis.kinopoisk.features.presenter.KinopoiskView;

import java.util.ArrayList;
import java.util.List;

public class FilmsListFragment extends Fragment implements FilmsListView{

    private final int SPAN_COUNT = 2;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private FilmAdapter adapter;
    private FilmsListPresenter filmsListPresenter;
    private View content;
    private Toolbar toolbar;
    private List<String> genersList;
    private List<CheckBox> genersListCheckBox;

    public void setPresenter(FilmsListPresenter presenter){
        filmsListPresenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        content = inflater.inflate(R.layout.list_films_layout, container,false);

        toolbar = (Toolbar) content.findViewById(R.id.toolbar_list_film);
        if (toolbar != null){
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        progressBar = (ProgressBar) content.findViewById(R.id.list_progress);

        recyclerView = (RecyclerView) content.findViewById(R.id.list_recycler);
        adapter = new FilmAdapter(getContext(), new FilmAdapter.SelectFilmListener() {
            @Override
            public void onFilmSelect(Film film) {
               filmsListPresenter.onFilmSelected(film, (KinopoiskView) getActivity());
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), SPAN_COUNT));


        /**<DrawerLayout>*/
        DrawerLayout drawer = (DrawerLayout) content.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        CharSequence  mDrawerTitle = getActivity().getTitle();
        DrawerLayout mDrawerLayout = (DrawerLayout) content.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout,
                R.string.nav_header_desc, R.string.nav_header_desc) {
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActivity().invalidateOptionsMenu();
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

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
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showGeners(List<Film> filmList){
        genersList = filmsListPresenter.getGeners(filmList);

        genersListCheckBox = new ArrayList<CheckBox>();

        LinearLayout containerLayout = (LinearLayout) getActivity().findViewById(R.id.container_drawer);

        for(int i = 0; i < genersList.size(); i++){

            genersListCheckBox.add(new CheckBox(getActivity()));

            genersListCheckBox.get(i).setText(genersList.get(i));
            genersListCheckBox.get(i).setChecked(false);

            LinearLayout.LayoutParams checkBoxLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            genersListCheckBox.get(i).setLayoutParams(checkBoxLayoutParams);

            containerLayout.addView(genersListCheckBox.get(i));
        }

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
