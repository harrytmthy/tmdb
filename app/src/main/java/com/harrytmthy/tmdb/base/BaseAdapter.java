package com.harrytmthy.tmdb.base;

import com.harrytmthy.tmdb.common.OnItemClickListener;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version BaseAdapter, v 0.1 2019-12-14 19:41 by Harry Timothy
 *
 * @param <D> Domain model output type.
 * @param <VH> ViewHolder type.
 */
public abstract class BaseAdapter<D, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<D> items;

    protected OnItemClickListener<D> listener;

    public void addItems(List<D> items) {
        this.items.addAll(items);
        this.notifyItemRangeInserted(items.size(), items.size());
    }

    public D getItem(int position) {
        return items.get(position);
    }

    @Override public int getItemCount() {
        return (items == null || items.isEmpty()) ? 0 : items.size();
    }

}