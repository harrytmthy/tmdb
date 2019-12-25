package com.harrytmthy.tmdb.movie.list;

import com.harrytmthy.domain.movie.model.Movie;
import com.harrytmthy.tmdb.R;
import com.harrytmthy.tmdb.base.BaseAdapter;
import com.harrytmthy.tmdb.databinding.ItemMovieBinding;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieAdapter, v 0.1 2019-12-14 19:57 by Harry Timothy
 */
public class MovieAdapter extends BaseAdapter<Movie, MovieAdapter.ViewHolder> {

    @Inject
    public MovieAdapter() {
        this.items = new ArrayList<>();
    }

    void removeMovieById(int movieId) {
        for(int i = 0; i < items.size(); i++) {
            if(items.get(i).getId() == movieId) {
                items.remove(i);
                notifyItemRemoved(i);
                return;
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
            R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
        holder.binding.poster.setOnClickListener(v -> listener.onItemClick(getItem(position)));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        final ItemMovieBinding binding;

        ViewHolder(ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Movie movie) {
            if(movie == null) return;
            binding.setMovie(movie);
        }

    }

}