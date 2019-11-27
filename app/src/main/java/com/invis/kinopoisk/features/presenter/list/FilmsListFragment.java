package com.invis.kinopoisk.features.presenter.list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    private FloatingActionButton drawerButton;
    private List<String> genersList;
    private List<TextView> genersListTexView;
    private List<Film> fulFilmList;

    private DrawerLayout mDrawerLayout;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private ActionBarDrawerToggle mDrawerToggle;


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
        }

        drawerButton = (FloatingActionButton) content.findViewById(R.id.drawer_open);

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
        mDrawerLayout = (DrawerLayout) content.findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout,
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

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        drawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

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
        Toolbar toolbarDrawer = (Toolbar) content.findViewById(R.id.toolbar_drawer);
        if (toolbarDrawer != null){
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbarDrawer);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.geners);
        }

        genersList = filmsListPresenter.getGeners(filmList);
        genersListTexView = new ArrayList<TextView>();

        LinearLayout containerLayout = (LinearLayout) getActivity().findViewById(R.id.container_drawer);

        for(int i = 0; i < genersList.size(); i++){
            genersListTexView.add(new TextView(getActivity()));

            containerLayout.addView(genersListTexView.get(i));

            genersListTexView.get(i).setPadding(20,10,10,10);
            genersListTexView.get(i).setTextSize(15);
            genersListTexView.get(i).setText(genersList.get(i));
            genersListTexView.get(i).setHeight(70);
            genersListTexView.get(i).setSelected(false);

            genersListTexView.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(view.isSelected())
                    {
                        view.setSelected(false);
                        view.setBackgroundColor(getResources().getColor(R.color.whiteBackground));
                        adapter.refreshFilms(fulFilmList);
                    } else {

                        for (int i = 0; i < genersListTexView.size(); i++) {
                            if (genersListTexView.get(i).isSelected()) {
                                genersListTexView.get(i).
                                        setBackgroundColor(getResources().getColor(R.color.whiteBackground));
                            }
                        }

                        view.setSelected(true);
                        view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
                        List<String> viewGeners = new ArrayList<String>();
                        viewGeners.add(((TextView) view).getText().toString());
                        filmsListPresenter.selectGeners(fulFilmList, viewGeners);
                    }
                }
                });

            LinearLayout.LayoutParams textViewLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            genersListTexView.get(i).setLayoutParams(textViewLayoutParams);
        }
    }

    @Override
    public void addFilmList(List<Film> filmList) {
        fulFilmList = filmList;
        adapter.addFilms(filmList);
    }

    @Override
    public void refreshFilmList(List<Film> filmList){
        adapter.refreshFilms(filmList);
    }

    @Override
    public void showError(String message) {
        Snackbar.make(content, message, Snackbar.LENGTH_SHORT).show();
    }
}
