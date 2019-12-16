package com.harrytmthy.tmdb.movie;

import com.harrytmthy.domain.movie.model.PagedMovie;
import com.harrytmthy.tmdb.R;
import com.harrytmthy.tmdb.base.BaseActivity;
import com.harrytmthy.tmdb.databinding.ActivityMovieBinding;
import com.harrytmthy.tmdb.movie.model.MovieAction;
import com.harrytmthy.tmdb.movie.model.MovieState;

import android.os.Bundle;
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
        presenter.setAction(new MovieAction.Initial());
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
        presenter.setAction(new MovieAction.LoadPopularMovies(1));
    }

    @Override
    public void renderDataState(PagedMovie pagedMovie) {
        adapter.addItems(pagedMovie.getMovies());
        presenter.setLoadNextPage();
    }

    @Override
    public void renderErrorState(Throwable error) {
        Toast.makeText(this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        presenter.unbind();
    }

}