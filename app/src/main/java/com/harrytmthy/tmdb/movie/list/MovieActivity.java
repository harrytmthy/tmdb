package com.harrytmthy.tmdb.movie.list;

import com.harrytmthy.domain.movie.model.PagedMovie;
import com.harrytmthy.tmdb.R;
import com.harrytmthy.tmdb.base.BaseActivity;
import com.harrytmthy.tmdb.constants.AppConstants;
import com.harrytmthy.tmdb.databinding.ActivityMovieBinding;
import com.harrytmthy.tmdb.movie.detail.MovieDetailActivity;
import com.harrytmthy.tmdb.movie.list.model.MovieAction;
import com.harrytmthy.tmdb.movie.list.model.MovieState;

import org.jetbrains.annotations.NotNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieActivity, v 0.1 2019-12-12 14:43 by Harry Timothy
 */
public class MovieActivity extends BaseActivity implements MovieView<MovieState> {

    @Inject MoviePresenter presenter;

    @Inject MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        ActivityMovieBinding binding = DataBindingUtil.setContentView(this,
            R.layout.activity_movie);
        binding.setPresenter(presenter);
        binding.setAdapter(adapter);

        adapter.setListener(movie -> startActivity(new Intent(this, MovieDetailActivity.class)
        .putExtra(getString(R.string.key_movie_id), movie.getId())));

        presenter.bind(this);
        dispatchAction(new MovieAction.LoadPopularMovies());
    }

    private void dispatchAction(MovieAction action) {
        if(getSupportActionBar() == null) return;
        if(action instanceof MovieAction.LoadPopularMovies) {
            getSupportActionBar().setTitle(getString(R.string.activity_movie_title_popular));
        } else if(action instanceof MovieAction.LoadTopRatedMovies) {
            getSupportActionBar().setTitle(getString(R.string.activity_movie_title_top_rated));
        } else getSupportActionBar().setTitle(getString(R.string.activity_movie_title_favorite));
        presenter.doAction(action);
    }

    @Override
    public void render(MovieState state) {
        if(state instanceof MovieState.Data) renderDataState(((MovieState.Data) state).data);
        else if(state instanceof MovieState.Error) renderErrorState(((MovieState.Error) state).error);
    }

    @Override
    public void renderDataState(PagedMovie pagedMovie) {
        presenter.setCurrentPage(pagedMovie.getPage());
        if(pagedMovie.getPage() > 1) adapter.addItems(pagedMovie.getMovies());
        else adapter.setItems(pagedMovie.getMovies());
        if(pagedMovie.getPage() < pagedMovie.getTotalPages()) presenter.setCanLoadNextPage(true);
    }

    @Override
    public void renderErrorState(Throwable error) {
        handleError(error);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConstants.ACTIVITY_LOGIN_REQUEST_CODE) {
            if(resultCode == RESULT_OK) presenter.doAction(new MovieAction.Refresh());
            else dispatchAction(new MovieAction.LoadPopularMovies());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        if(getSupportActionBar() == null) return super.onOptionsItemSelected(item);
        final int itemId = item.getItemId();
        if(itemId == R.id.popular) dispatchAction(new MovieAction.LoadPopularMovies());
        else if(itemId == R.id.topRated) dispatchAction(new MovieAction.LoadTopRatedMovies());
        else dispatchAction(new MovieAction.LoadFavoriteMovies());
        return true;
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        presenter.unbind();
    }

}