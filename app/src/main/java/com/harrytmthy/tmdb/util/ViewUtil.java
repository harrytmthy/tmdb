package com.harrytmthy.tmdb.util;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.harrytmthy.data.common.DataConstants;
import com.harrytmthy.tmdb.R;
import com.harrytmthy.tmdb.base.BaseAdapter;
import com.harrytmthy.tmdb.common.AppConstants;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version ViewUtil, v 0.1 2019-12-13 11:27 by Harry Timothy
 */
public final class ViewUtil {

    @BindingAdapter("animateRotation")
    public static void animateRotation(View view, boolean animate) {
        if(animate) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(view,
                "rotation",
                0f, 360f);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setInterpolator(new LinearInterpolator());
            animator.setDuration(AppConstants.ROTATION_DURATION);
            animator.start();
        } else {
            view.clearAnimation();
        }
    }

    @BindingAdapter("loadFromPath")
    public static void loadFromPath(ImageView imageView, String path) {
        boolean isPathEmpty = path == null || path.isEmpty();
        Glide.with(imageView.getContext())
            .load(isPathEmpty ? R.drawable.ic_no_image : DataConstants.IMAGE_URL + path)
            .transition(DrawableTransitionOptions.withCrossFade())
            .fitCenter()
            .into(imageView);
    }

    @BindingAdapter("adapter")
    public static void bindAdapter(RecyclerView recyclerView, BaseAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("onScroll")
    public static void onScroll(RecyclerView recyclerView, Runnable onScroll) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager =
                    (LinearLayoutManager) recyclerView.getLayoutManager();
                if(layoutManager == null) return;
                int totalItemCount = layoutManager.getItemCount();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (totalItemCount == lastVisibleItemPosition + 1) {
                    onScroll.run();
                }
            }
        });
    }

}
