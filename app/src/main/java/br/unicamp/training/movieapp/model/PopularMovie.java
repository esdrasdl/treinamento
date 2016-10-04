package br.unicamp.training.movieapp.model;

import org.json.JSONObject;

public class PopularMovie {

    public static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500";

    private boolean adult;

    private String backdropPath;

    private String id;

    private String originalTitle;

    private String releaseDate;

    private String posterPath;

    private double popularity;

    private String title;

    private double voteAverage;

    private int voteCount;

    private String overview;

    private boolean movieReady;

    public PopularMovie() {
    }

    public PopularMovie(JSONObject item) {
        setAdult(item.optBoolean("adult"));
        setTitle(item.optString("title"));
        setOriginalTitle(item.optString("original_title"));
        setOverview(item.optString("overview"));
        setId(item.optString("id"));
        setVoteCount(item.optInt("vote_count"));
        setBackdropPath(item.optString("backdrop_path"));
        setPosterPath(item.optString("poster_path"));
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public boolean isMovieReady() {
        return movieReady;
    }

    public void setMovieReady(boolean movieReady) {
        this.movieReady = movieReady;
    }
}
