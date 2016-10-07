package br.unicamp.training.movieapp.ui;

import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.List;

import br.unicamp.training.movieapp.R;
import br.unicamp.training.movieapp.manager.MainScreenManager;
import br.unicamp.training.movieapp.model.Movie;
import br.unicamp.training.movieapp.model.Page;
import br.unicamp.training.movieapp.ui.adapter.PopularMovieAdapter;
import br.unicamp.training.movieapp.utils.DeviceUtils;
import br.unicamp.training.movieapp.utils.PrefUtils;

public class MoviesActivity extends AppCompatActivity {

    private GridView mGridView;
    private ContentLoadingProgressBar mProgressBar;
    private MainScreenManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        mManager = new MainScreenManager();
        setupViews();
        loadMovies();
    }

    private void setupViews() {
        mGridView = (GridView) findViewById(R.id.main_gridview);
        mProgressBar = (ContentLoadingProgressBar) findViewById(R.id.main_progress_bar);
    }

    private void loadMovies() {
        mProgressBar.setVisibility(View.VISIBLE);
        if (DeviceUtils.isConnected(this)) {
            MainScreenManager.SmartCallback callback = new MainScreenManager.SmartCallback() {
                @Override
                public void onSuccess(Page result) {
                    if (result.movies != null) {
                        HashSet<String> names = new HashSet<>();
                        mManager.encryptTitles(result, names);
                        mManager.saveMovies(MoviesActivity.this, result.movies);
                        PrefUtils.setMovieNames(MoviesActivity.this, names);
                        PopularMovieAdapter adapter = new PopularMovieAdapter(result.movies, MoviesActivity.this);
                        mGridView.setAdapter(adapter);
                    }
                    mProgressBar.setVisibility(View.GONE);
                }
            };
            mManager.getList(callback);
        } else {
            List<Movie> movies = mManager.getMovies(this);
            if (movies.size() > 0) {
                PopularMovieAdapter adapter = new PopularMovieAdapter(movies, MoviesActivity.this);
                mGridView.setAdapter(adapter);
                mProgressBar.setVisibility(View.GONE);
            } else {
                Toast.makeText(this, getString(R.string.internet_error), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
