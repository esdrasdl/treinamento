package br.unicamp.training.movieapp.manager;

import android.content.Context;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import br.unicamp.training.movieapp.model.Page;
import br.unicamp.training.movieapp.model.PopularMovie;
import br.unicamp.training.movieapp.services.Service;
import br.unicamp.training.movieapp.ui.MoviesActivity;
import br.unicamp.training.movieapp.utils.Constants;
import br.unicamp.training.movieapp.utils.PrefUtils;
import br.unicamp.training.movieapp.utils.SecurityUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainScreenManager {
    private final SecurityUtils mSecurityUtils;

    public MainScreenManager() {
        mSecurityUtils = new SecurityUtils();
    }

    public void getList(final SmartCallback smartCallback) {
        Call<Page> call = Service.getPopularMovieAPI().getPage(Constants.API_KEY, 2);
        call.enqueue(new Callback<Page>() {
            @Override
            public void onResponse(Call<Page> call, Response<Page> response) {
                if (response.isSuccessful()) {
                    smartCallback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    public void encryptTitles(Page result, HashSet<String> names) {
        for (PopularMovie movie : result.movies) {
            String title = movie.getTitle();
            title = mSecurityUtils.encode(title);
            names.add(title);
        }
    }

    public void printTitles(Context context) {
        Set<String> names = PrefUtils.getMovieNames(context);
        for (String name : names) {
            Log.d(MoviesActivity.class.getSimpleName(), mSecurityUtils.decode(name));
        }
    }

    public interface SmartCallback {
        void onSuccess(Page result);
    }

}
