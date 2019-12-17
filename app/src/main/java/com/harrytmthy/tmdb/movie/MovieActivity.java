package com.harrytmthy.tmdb.movie;

import com.harrytmthy.domain.movie.model.PagedMovie;
import com.harrytmthy.tmdb.R;
import com.harrytmthy.tmdb.base.BaseActivity;
import com.harrytmthy.tmdb.databinding.ActivityMovieBinding;
import com.harrytmthy.tmdb.movie.model.MovieAction;
import com.harrytmthy.tmdb.movie.model.MovieState;

import org.jetbrains.annotations.NotNull;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import javax.inject.Inject;

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

        adapter.setListener(item -> {
            // TODO: Start MovieDetailActivity
        });

        ActivityMovieBinding binding = DataBindingUtil.setContentView(this,
            R.layout.activity_movie);
        binding.setLifecycleOwner(this);
        binding.setPresenter(presenter);
        binding.setAdapter(adapter);

        presenter.bind(this);
        presenter.doAction(new MovieAction.Initial());
    }

    @Override
    public void render(MovieState state) {
        presenter.state.set(state);
        if(state instanceof MovieState.Loading) renderLoadingState();
        if(state instanceof MovieState.Data) renderDataState(((MovieState.Data) state).data);
        if(state instanceof MovieState.Error) renderErrorState(((MovieState.Error) state).error);
    }

    @Override
    public void renderLoadingState() {
        presenter.doAction(presenter.getDefaultAction());
    }

    @Override
    public void renderDataState(PagedMovie pagedMovie) {
        if(pagedMovie.getPage() > 1) adapter.addItems(pagedMovie.getMovies());
        else adapter.setItems(pagedMovie.getMovies());
        presenter.canLoadNextPage();
    }

    @Override
    public void renderErrorState(Throwable error) {
        Toast.makeText(this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
        if(itemId == R.id.popular) {
            presenter.setDefaultAction(new MovieAction.LoadPopularMovies(1));
            getSupportActionBar().setTitle(getString(R.string.activity_movie_popular_title));
        } else if(itemId == R.id.topRated) {
            presenter.setDefaultAction(new MovieAction.LoadTopRatedMovies(1));
            getSupportActionBar().setTitle(getString(R.string.activity_movie_top_rated_title));
        } else return super.onOptionsItemSelected(item);
        presenter.doAction(new MovieAction.Initial());
        return true;
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        presenter.unbind();
    }

}