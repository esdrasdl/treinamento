package br.unicamp.training.movieapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import br.unicamp.training.movieapp.manager.DatabaseManager;

public class Movie {
    public static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500";

    //region Database column
    public static String TABLE_NAME = "movie";
    public static String COLUMN_ID = "_id";
    public static String COLUMN_ORIGINAL_TITLE = "original_title";
    public static String COLUMN_TITLE = "title";
    public static String COLUMN_OVERVIEW = "overview";
    public static String COLUMN_VOTE_AVERAGE = "vote_average";
    public static String COLUMN_VOTE_COUNT = "vote_count";
    public static String COLUMN_BACKDROP_PATH = "backdrop_path";
    public static String COLUMN_POSTER_PATH = "poster_path";
    //endregion

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("id")
    private Long id;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("title")
    private String title;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("overview")
    private String overview;

    @SerializedName("movieReady")
    private boolean movieReady;

    public Movie() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Movie(Cursor cursor) {
        setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
        setOriginalTitle(cursor.getString(cursor.getColumnIndex(COLUMN_ORIGINAL_TITLE)));
        setOverview(cursor.getString(cursor.getColumnIndex(COLUMN_OVERVIEW)));
        setVoteAverage(cursor.getDouble(cursor.getColumnIndex(COLUMN_VOTE_AVERAGE)));
        setVoteCount(cursor.getInt(cursor.getColumnIndex(COLUMN_VOTE_COUNT)));
        setBackdropPath(cursor.getString(cursor.getColumnIndex(COLUMN_BACKDROP_PATH)));
        setPosterPath(cursor.getString(cursor.getColumnIndex(COLUMN_POSTER_PATH)));
    }

    public static String createTable() {
        String sql = "CREATE TABLE " + TABLE_NAME + " ( " +
                COLUMN_ID + " INT , " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_ORIGINAL_TITLE + " TEXT, " +
                COLUMN_OVERVIEW + " TEXT, " +
                COLUMN_VOTE_AVERAGE + " DOUBLE, " +
                COLUMN_BACKDROP_PATH + " TEXT, " +
                COLUMN_POSTER_PATH + " TEXT, " +
                COLUMN_VOTE_COUNT + " INT); ";
        Log.d(Movie.class.getSimpleName(), "Sql: " + sql);
        return sql;
    }

    public void save(Context context) {
        DatabaseManager databaseManager = new DatabaseManager();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, getId());
        contentValues.put(COLUMN_TITLE, getTitle());
        contentValues.put(COLUMN_ORIGINAL_TITLE, getOriginalTitle());
        contentValues.put(COLUMN_OVERVIEW, getOverview());
        contentValues.put(COLUMN_VOTE_AVERAGE, getVoteAverage());
        contentValues.put(COLUMN_VOTE_COUNT, getVoteCount());
        contentValues.put(COLUMN_BACKDROP_PATH, getBackdropPath());
        contentValues.put(COLUMN_POSTER_PATH, getPosterPath());

        if (!databaseManager.existMovie(context, getId())) {
            databaseManager.saveMovie(context, contentValues);
        }
    }

}
