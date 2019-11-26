package com.invis.kinopoisk.features.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Film implements Parcelable {
    private int id;
    private String localized_name;
    private String name;
    private int year;
    private double rating;
    private String image_url;
    private String description;
    private List<String> genres;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.localized_name);
        dest.writeString(this.name);
        dest.writeInt(this.year);
        dest.writeDouble(this.rating);
        dest.writeString(this.image_url);
        dest.writeString(this.description);
        dest.writeStringList(this.genres);
    }

    protected Film(Parcel in) {
        this.id = in.readInt();
        this.localized_name = in.readString();
        this.name = in.readString();
        this.year = in.readInt();
        this.rating = in.readDouble();
        this.image_url = in.readString();
        this.description = in.readString();
        this.genres = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Film> CREATOR = new Parcelable.Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel source) {
            return new Film(source);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };
}
