package br.unicamp.training.movieapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.unicamp.training.movieapp.model.Movie;

public class MovieDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "movies-db";

    private static MovieDatabaseHelper sInstance;

    public static synchronized MovieDatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MovieDatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private MovieDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Movie.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
