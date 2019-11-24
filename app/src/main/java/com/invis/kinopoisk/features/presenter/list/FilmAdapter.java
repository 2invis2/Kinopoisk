package com.invis.kinopoisk.features.presenter.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.invis.kinopoisk.R;
import com.invis.kinopoisk.features.Entity.Film;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmHolder> {
    
    private final List<Film> films = new ArrayList<>();
    private final LayoutInflater inflater;
    private final SelectFilmListener selectFilmListener;

    FilmAdapter(Context context, SelectFilmListener selectFilmListener) {
        inflater = LayoutInflater.from(context);
        this.selectFilmListener = selectFilmListener;
    }

    @NonNull
    @Override
    public FilmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.film_item, parent, false);
        return new FilmHolder(itemView, selectFilmListener);
    }

    public void clearFilmAdapter(){
        films.clear();
    }

    @Override
    public void onBindViewHolder(@NonNull FilmHolder holder, int position) {
        holder.bind(films.get(position));
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public void setFilms(List<Film> FilmList) {
        List<Film> FilmListTmp = new ArrayList<>();
        FilmListTmp.addAll(FilmList);
        films.clear();
        films.addAll(FilmListTmp);
        notifyDataSetChanged();
    }

    public void addFilms(List<Film> filmList) {
        films.addAll(filmList);
        notifyDataSetChanged();
    }

    public List<Film> getFilms(){
        return films;
    }

    class FilmHolder extends RecyclerView.ViewHolder {

        private final TextView filmNameView;
        private final ImageView filmImageView;
        private final SelectFilmListener selectFilmListener;

        FilmHolder(View view, SelectFilmListener selectBookListener) {
            super(view);
            this.selectFilmListener = selectBookListener;
            filmImageView = view.findViewById(R.id.film_image_item);
            filmNameView = view.findViewById(R.id.film_name_item);
        }

        void bind(final Film film) {

            Picasso.get()
                    .load(film.getImage_url())
                    .placeholder(R.drawable.film_placeholder)
                    .error(R.drawable.film_placeholder_error)
                    .into(filmImageView);

            filmNameView.setText(film.getLocalized_name());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectFilmListener.onFilmSelect(film);
                }
            });
        }
    }

    interface SelectFilmListener {

        void onFilmSelect(Film Film);
    }

}