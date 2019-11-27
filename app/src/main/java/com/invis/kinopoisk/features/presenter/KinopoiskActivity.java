package com.invis.kinopoisk.features.presenter;

import android.os.Build;
import android.os.Bundle;
import android.support.transition.Fade;
import android.support.transition.Slide;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;

import com.invis.kinopoisk.R;
import com.invis.kinopoisk.features.Entity.Film;
import com.invis.kinopoisk.features.presenter.description.DescriptionFragment;
import com.invis.kinopoisk.features.presenter.list.FilmsListFragment;

public class KinopoiskActivity extends AppCompatActivity implements KinopoiskView{

    private Fragment filmsListFragment;
    private Fragment descriptionFragment;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kinopoisk);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        filmsListFragment = new FilmsListFragment();
        fragmentTransaction.add(R.id.container, filmsListFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onFilmSelect(Film film) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        descriptionFragment = DescriptionFragment.newInstance(film);

        /**анимация смены фрагментов*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            descriptionFragment.setEnterTransition(new Slide(Gravity.RIGHT));
            filmsListFragment.setExitTransition(new Fade());
        }

        fragmentTransaction
                .replace(R.id.container, descriptionFragment)
                .addToBackStack("kinopoisk")
                .commit();
    }

    @Override
    public void onBackPressed() {


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)){
                drawer.closeDrawer(GravityCompat.START);
            }
        } else {
            /**проверка на пустоту стека фрагментов*/
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (fragmentManager.getBackStackEntryCount() != 0) {
                super.onBackPressed();
                fragmentManager.popBackStack("kinopoisk", 0);
            }
        }
    }
}