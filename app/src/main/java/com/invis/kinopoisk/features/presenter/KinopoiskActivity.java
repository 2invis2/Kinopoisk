package com.invis.kinopoisk.features.presenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.invis.kinopoisk.R;
import com.invis.kinopoisk.features.Entity.Film;
import com.invis.kinopoisk.features.presenter.description.DescriptionFragment;
import com.invis.kinopoisk.features.presenter.list.FilmsListFragment;

public class KinopoiskActivity extends BaseActivity implements KinopoiskView{

    private KinopoiskPresenter kinopoiskPresenter;

    private Fragment filmsListFragment;
    private Fragment descriptionFragment;
    private Toolbar toolbar;


    @Override
    protected MvpPresenter<KinopoiskView> getPresenter() {
        kinopoiskPresenter = KinopoiskPresenterFactory.createPresenter(this);
        return kinopoiskPresenter;
    }

    @Override
    protected KinopoiskView getMvpView() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kinopoisk);

        /*toolbar = (Toolbar) findViewById(R.id.toolbar_list_film);
        if (toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }*/

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        filmsListFragment = new FilmsListFragment();
        ((FilmsListFragment) filmsListFragment).setPresenter(kinopoiskPresenter.getFilmsListPresenter());
        fragmentTransaction.add(R.id.container, filmsListFragment)
                .addToBackStack(null)
                .commit();


    }

    @Override
    protected void onStart(){
        super.onStart();
    }



    @Override
    public void onFilmSelect(Film film) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        descriptionFragment = DescriptionFragment.newInstance(film);

        fragmentTransaction.add(R.id.container, descriptionFragment)
                .addToBackStack("kinopoisk")
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack("kinopoisk", 0);
    }


}