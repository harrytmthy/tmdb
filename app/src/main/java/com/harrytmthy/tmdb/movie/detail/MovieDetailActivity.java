package com.harrytmthy.tmdb.movie.detail;

import com.harrytmthy.domain.movie.model.MovieDetail;
import com.harrytmthy.tmdb.R;
import com.harrytmthy.tmdb.base.BaseActivity;
import com.harrytmthy.tmdb.databinding.ActivityMovieDetailBinding;
import com.harrytmthy.tmdb.movie.detail.model.MovieDetailAction;
import com.harrytmthy.tmdb.movie.detail.model.MovieDetailState;

import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import androidx.databinding.DataBindingUtil;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieDetailActivity, v 0.1 2019-12-20 10:45 by Harry Timothy
 */
public class MovieDetailActivity extends BaseActivity implements MovieDetailView<MovieDetailState> {

    @Inject MovieDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        int movieId = getIntent().getIntExtra(getString(R.string.key_movie_id), -1);
        if(movieId < 0 || getSupportActionBar() == null) {
            Toast.makeText(this, getString(R.string.activity_movie_detail_error), Toast.LENGTH_SHORT).show();
            onBackPressed();
            return;
        }

        ActivityMovieDetailBinding binding = DataBindingUtil.setContentView(this,
            R.layout.activity_movie_detail);
        binding.setPresenter(presenter);

        presenter.bind(this);
        presenter.doAction(new MovieDetailAction.LoadDetails(movieId));
        getSupportActionBar().setTitle(getString(R.string.activity_movie_detail_title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void render(MovieDetailState state) {
        if(state instanceof MovieDetailState.Data) renderDataState(((MovieDetailState.Data) state).data);
        if(state instanceof MovieDetailState.Error) renderErrorState(((MovieDetailState.Error) state).error);
    }

    @Override
    public void renderDataState(MovieDetail movieDetail) {
        presenter.movieDetail.set(movieDetail);
        //TODO: Add Trailers
    }

    @Override
    public void renderErrorState(Throwable error) {

    }

}