package com.invis.kinopoisk.features.Entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Film {
    private int id;
    private String localized_name;
    private String name;
    private int year;
    private double rating;
    private String image_url;
    private String description;
    private List<String> genres;
}
