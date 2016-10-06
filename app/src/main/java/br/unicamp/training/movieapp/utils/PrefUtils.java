package br.unicamp.training.movieapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PrefUtils {

    private static final String PREF_MOVIE_NAME = "movie_name";

    public static Set<String> getMovieNames(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String names = sp.getString(PREF_MOVIE_NAME, "");
        return new HashSet<>(Arrays.asList(TextUtils.split(names, ";")));
    }

    public static void setMovieNames(Context context, Set<String> nameSet) {
        Set<String> names = getMovieNames(context);
        names.addAll(nameSet);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(PREF_MOVIE_NAME, TextUtils.join(";", names)).apply();
    }

}
