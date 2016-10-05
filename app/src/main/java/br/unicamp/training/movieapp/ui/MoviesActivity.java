package br.unicamp.training.movieapp.ui;

import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import br.unicamp.training.movieapp.R;
import br.unicamp.training.movieapp.manager.MainScreenManager;
import br.unicamp.training.movieapp.model.PopularMovie;
import br.unicamp.training.movieapp.ui.adapter.PopularMovieAdapter;

public class MoviesActivity extends AppCompatActivity {

    private GridView mGridView;
    private ContentLoadingProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        setupViews();
        loadMovies();
    }

    private void setupViews() {
        mGridView = (GridView) findViewById(R.id.main_gridview);
        mProgressBar = (ContentLoadingProgressBar) findViewById(R.id.main_progress_bar);
    }

    private void loadMovies() {
        MainScreenManager manager = new MainScreenManager();
        MainScreenManager.InternetCallback popularMovies = new MainScreenManager.InternetCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    String moviesJsonString = new JSONObject(result).optJSONArray("results").toString();

                    PopularMovie[] movies = new Gson().fromJson(moviesJsonString, PopularMovie[].class);

                    if (movies != null) {
                        PopularMovieAdapter adapter = new PopularMovieAdapter(movies, MoviesActivity.this);
                        mGridView.setAdapter(adapter);
                    }
                    mProgressBar.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        mProgressBar.setVisibility(View.VISIBLE);
        manager.getSimpleJson(popularMovies);
    }

}
