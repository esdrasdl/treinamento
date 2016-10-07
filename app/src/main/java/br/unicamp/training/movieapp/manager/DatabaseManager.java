package br.unicamp.training.movieapp.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.unicamp.training.movieapp.db.MovieDatabaseHelper;
import br.unicamp.training.movieapp.model.Movie;

public class DatabaseManager {

    public List<Movie> getAllMovies(Context context) {
        List<Movie> result = new ArrayList<>();
        SQLiteDatabase database = MovieDatabaseHelper.getInstance(context).getReadableDatabase();
        //String table, String[] columns, String selection,
        Cursor cursor = database.query(Movie.TABLE_NAME, new String[]{
                        Movie.COLUMN_ID,
                        Movie.COLUMN_TITLE,
                        Movie.COLUMN_ORIGINAL_TITLE,
                        Movie.COLUMN_OVERVIEW,
                        Movie.COLUMN_VOTE_AVERAGE,
                        Movie.COLUMN_VOTE_COUNT,
                        Movie.COLUMN_BACKDROP_PATH,
                        Movie.COLUMN_POSTER_PATH
                },      //String[] selectionArgs, String groupBy, String having, String orderBy
                null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                result.add(new Movie(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }

        database.close();
        return result;
    }

    public void saveMovie(Context context, ContentValues contentValues) {
        SQLiteDatabase database = MovieDatabaseHelper.getInstance(context).getWritableDatabase();
        database.insert(Movie.TABLE_NAME, null, contentValues);
        database.close();
    }

    public Movie getMovie(Context context, long id) {
        Movie result = null;
        SQLiteDatabase database = MovieDatabaseHelper.getInstance(context).getReadableDatabase();
        Cursor cursor = database.query(Movie.TABLE_NAME, new String[]{
                        Movie.COLUMN_ID,
                        Movie.COLUMN_TITLE,
                        Movie.COLUMN_ORIGINAL_TITLE,
                        Movie.COLUMN_OVERVIEW,
                        Movie.COLUMN_VOTE_AVERAGE,
                        Movie.COLUMN_VOTE_COUNT,
                        Movie.COLUMN_BACKDROP_PATH,
                        Movie.COLUMN_POSTER_PATH},
                Movie.COLUMN_ID + "=? ",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                result = new Movie(cursor);
            }
            cursor.close();
        }
        database.close();
        return result;
    }

    public boolean existMovie(Context context, long id) {
        return getMovie(context, id) != null;
    }

}
