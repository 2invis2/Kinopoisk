package com.invis.kinopoisk.features.presenter.description;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.invis.kinopoisk.R;
import com.invis.kinopoisk.features.Entity.Film;
import com.squareup.picasso.Picasso;

public class DescriptionFragment extends Fragment implements DescriptionView {

    private Film film;

    private View content;

    private ImageView imageDescription;
    private TextView localizedNameDescription;
    private TextView nameDescription;
    private TextView yearDescription;
    private TextView ratingDescription;
    private TextView textDescription;
    private Toolbar toolbar;


    public static DescriptionFragment newInstance(Film filmSelected) {
        DescriptionFragment descriptionFragment = new DescriptionFragment();
        Bundle args = new Bundle();
        args.putParcelable("filmSelected", filmSelected);
        descriptionFragment.setArguments(args);
        return descriptionFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        film = getArguments().getParcelable("filmSelected");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        content = inflater.inflate(R.layout.description_layout, container,false);

        toolbar = (Toolbar) content.findViewById(R.id.toolbar_description);
        if (toolbar != null){
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(film.getLocalized_name());
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().onBackPressed();
                }
            });
        }

        showFilm();

        return content;
    }

    protected DescriptionView getMvpView(){
        return this;
    }

    public void setFilm(Film film){
        this.film = film;
    }

    @Override
    public void showFilm() {
        imageDescription = (ImageView) content.findViewById(R.id.image_description);
        localizedNameDescription = (TextView) content.findViewById(R.id.localized_name_description);
        nameDescription = (TextView) content.findViewById(R.id.name_description);
        yearDescription = (TextView) content.findViewById(R.id.year_description);
        ratingDescription = (TextView) content.findViewById(R.id.rating_description);
        textDescription = (TextView) content.findViewById(R.id.text_description);

        if(film.getImage_url() != null) {
            Picasso.get()
                    .load(film.getImage_url())
                    .placeholder(R.drawable.film_placeholder)
                    .error(R.drawable.film_placeholder_error)
                    .into(imageDescription);
        } else {
            imageDescription.setImageResource(R.drawable.film_placeholder_error);
        }

        localizedNameDescription.setText(film.getLocalized_name());
        nameDescription.setText(film.getName());
        yearDescription.setText(R.string.year);
        yearDescription.setText(yearDescription.getText() + String.valueOf(film.getYear()));
        ratingDescription.setText(R.string.rating);
        ratingDescription.setText(ratingDescription.getText() + String.valueOf(film.getRating()));
        textDescription.setText(film.getDescription());
    }
}
