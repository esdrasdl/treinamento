package br.unicamp.training.movieapp.ui;

import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;

import java.util.HashSet;

import br.unicamp.training.movieapp.R;
import br.unicamp.training.movieapp.manager.MainScreenManager;
import br.unicamp.training.movieapp.model.Page;
import br.unicamp.training.movieapp.model.PopularMovie;
import br.unicamp.training.movieapp.ui.adapter.PopularMovieAdapter;
import br.unicamp.training.movieapp.utils.PrefUtils;

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
        MainScreenManager.SmartCallback callback = new MainScreenManager.SmartCallback() {
            @Override
            public void onSuccess(Page result) {
                if (result.movies != null) {

                    HashSet<String> names = new HashSet<>();
                    for (PopularMovie movie : result.movies) {
                        names.add(movie.getTitle());
                    }
                    PrefUtils.setMovieNames(MoviesActivity.this, names);
                    PopularMovieAdapter adapter = new PopularMovieAdapter(result.movies, MoviesActivity.this);
                    mGridView.setAdapter(adapter);
                }
                mProgressBar.setVisibility(View.GONE);
            }
        };

        mProgressBar.setVisibility(View.VISIBLE);
        manager.getList(callback);
    }

}
