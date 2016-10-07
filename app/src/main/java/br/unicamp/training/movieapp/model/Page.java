package br.unicamp.training.movieapp.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Page {

    @SerializedName("results")
    public List<Movie> movies;

}
