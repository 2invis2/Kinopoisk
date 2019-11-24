package com.invis.kinopoisk.features.presenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.invis.kinopoisk.R;
import com.invis.kinopoisk.features.presenter.list.FilmsListFragment;

public class KinopoiskActivity extends BaseActivity implements KinopoiskView{

    private KinopoiskPresenter kinopoiskPresenter;

    private Toolbar toolbar;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;


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

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null){
            setSupportActionBar(toolbar);
        }

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragment = new FilmsListFragment();
        ((FilmsListFragment) fragment).setPresenter(kinopoiskPresenter.getFilmsListPresenter());
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.commit();

        /**<DrawerLayout>*/
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        CharSequence  mDrawerTitle = getTitle();
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.nav_header_desc, R.string.nav_header_desc) {
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);*/
    }

    @Override
    protected void onStart(){
        super.onStart();
    }


}