package com.harrytmthy.tmdb.movie.detail;

import com.harrytmthy.domain.movie.model.Video;
import com.harrytmthy.tmdb.R;
import com.harrytmthy.tmdb.base.BaseAdapter;
import com.harrytmthy.tmdb.databinding.ItemMovieDetailBinding;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieDetailAdapter, v 0.1 2019-12-21 23:46 by Harry Timothy
 */
public class MovieDetailAdapter extends BaseAdapter<Video, MovieDetailAdapter.ViewHolder> {

    @Inject
    public MovieDetailAdapter() {
        this.items = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
            R.layout.item_movie_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
        holder.binding.getRoot().setOnClickListener(v -> listener.onItemClick(getItem(position)));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        final ItemMovieDetailBinding binding;

        ViewHolder(ItemMovieDetailBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Video video) {
            if(video == null) return;
            binding.setVideo(video);
        }

    }

}