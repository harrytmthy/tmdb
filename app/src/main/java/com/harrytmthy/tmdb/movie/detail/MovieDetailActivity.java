package com.harrytmthy.tmdb.movie.detail;

import com.harrytmthy.domain.movie.model.MovieDetail;
import com.harrytmthy.tmdb.R;
import com.harrytmthy.tmdb.base.BaseActivity;
import com.harrytmthy.tmdb.constants.AppConstants;
import com.harrytmthy.tmdb.databinding.ActivityMovieDetailBinding;
import com.harrytmthy.tmdb.movie.detail.model.MovieDetailAction;
import com.harrytmthy.tmdb.movie.detail.model.MovieDetailState;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieDetailActivity, v 0.1 2019-12-20 10:45 by Harry Timothy
 */
public class MovieDetailActivity extends BaseActivity implements MovieDetailView<MovieDetailState> {

    @Inject MovieDetailPresenter presenter;

    @Inject MovieDetailAdapter adapter;

    private ActivityMovieDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        final int movieId = getIntent().getIntExtra(getString(R.string.key_movie_id), -1);
        if(movieId < 0 || getSupportActionBar() == null) {
            Toast.makeText(this, getString(R.string.activity_movie_detail_error), Toast.LENGTH_SHORT).show();
            onBackPressed();
            return;
        } else presenter.setMovieId(movieId);

        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_movie_detail);
        binding.setPresenter(presenter);

        presenter.bind(this);
        presenter.doAction(new MovieDetailAction.LoadDetails());
        getSupportActionBar().setTitle(getString(R.string.activity_movie_detail_title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void startYoutubeActivity(String key) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + key)));
        } catch (ActivityNotFoundException ex) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(AppConstants.URL_YOUTUBE + key)));
        }
    }

    @Override
    public void render(MovieDetailState state) {
        if(state instanceof MovieDetailState.Data) renderDataState(((MovieDetailState.Data) state).data);
        else if(state instanceof MovieDetailState.Error) renderErrorState(((MovieDetailState.Error) state).error);
        else if(state instanceof MovieDetailState.Favorite) renderFavoriteState();
        else if(state instanceof MovieDetailState.Unfavorite) renderUnfavoriteState();
    }

    @Override
    public void renderDataState(MovieDetail movieDetail) {
        presenter.favorite.set(movieDetail.isFavorite());
        binding.markFavoriteButton.setOnClickListener(v -> presenter.markFavorite());
        binding.setMovieDetail(movieDetail);
        binding.setAdapter(adapter);
        adapter.setListener(movie -> startYoutubeActivity(movie.getKey()));
        adapter.setItems(movieDetail.getVideos());
    }

    @Override
    public void renderErrorState(Throwable error) {
        handleError(error);
    }

    @Override
    public void renderFavoriteState() {
        presenter.favorite.set(true);
        Toast.makeText(this,
            getString(R.string.activity_movie_detail_mark_favorite_success),
            Toast.LENGTH_SHORT).show();
    }

    @Override
    public void renderUnfavoriteState() {
        presenter.favorite.set(false);
        Toast.makeText(this,
            getString(R.string.activity_movie_detail_unmark_favorite_success),
            Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConstants.ACTIVITY_LOGIN_REQUEST_CODE) {
            if(resultCode == RESULT_OK) presenter.doAction(new MovieDetailAction.MarkFavorite());
        }
    }

}