package br.unicamp.training.movieapp.ui;

import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import java.util.HashSet;
import java.util.Set;

import br.unicamp.training.movieapp.R;
import br.unicamp.training.movieapp.manager.MainScreenManager;
import br.unicamp.training.movieapp.model.Page;
import br.unicamp.training.movieapp.model.PopularMovie;
import br.unicamp.training.movieapp.ui.adapter.PopularMovieAdapter;
import br.unicamp.training.movieapp.utils.PrefUtils;
import br.unicamp.training.movieapp.utils.SecurityUtils;

public class MoviesActivity extends AppCompatActivity {

    private GridView mGridView;
    private ContentLoadingProgressBar mProgressBar;
    private SecurityUtils mSecurityUtils;
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

        MainScreenManager.SmartCallback callback = new MainScreenManager.SmartCallback() {
            @Override
            public void onSuccess(Page result) {
                if (result.movies != null) {
                    HashSet<String> names = new HashSet<>();
                    mManager.encryptTitles(result, names);
                    PrefUtils.setMovieNames(MoviesActivity.this, names);
                    PopularMovieAdapter adapter = new PopularMovieAdapter(result.movies, MoviesActivity.this);
                    mGridView.setAdapter(adapter);
                }
                mProgressBar.setVisibility(View.GONE);
            }
        };

        mProgressBar.setVisibility(View.VISIBLE);
        mManager.getList(callback);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mManager.printTitles(this);
    }

}
