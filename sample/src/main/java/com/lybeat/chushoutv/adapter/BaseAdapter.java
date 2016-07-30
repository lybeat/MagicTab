package com.lybeat.chushoutv.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: lybeat
 * Date: 2016/3/4
 */
public abstract class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.BaseHolder> {

    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_HEADER = 1;
    private static final int TYPE_FOOTER = 2;

    private View headerView;
    private View footerView;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && headerView != null) {
            return TYPE_HEADER;
        } else if (position == getItemCount() - 1 && footerView != null) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headerView != null && viewType == TYPE_HEADER) {
            return new BaseHolder(headerView);
        } else if (footerView != null && viewType == TYPE_FOOTER) {
            return new BaseHolder(footerView);
        }
        return onCreateHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(final BaseHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_HEADER
                || getItemViewType(position) == TYPE_FOOTER)
            return;

        final int realPosition =  headerView == null ? position : position -1;
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.itemView,  realPosition);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onItemLongClick(holder.itemView,  realPosition);
                    return false;
                }
            });
        }

        onBindHolder(holder, realPosition);
    }

    @Override
    public int getItemCount() {
        int count;
        count = headerView == null ? 0 : 1;
        count = footerView == null ? count : count + 1;
        return count + getCount();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        final RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            ((GridLayoutManager) manager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (getItemViewType(position) == TYPE_HEADER
                            || getItemViewType(position) == TYPE_FOOTER) {
                        return ((GridLayoutManager) manager).getSpanCount();
                    } else {
                        return 1;
                    }
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(BaseHolder holder) {
        super.onViewAttachedToWindow(holder);

        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams
                && headerView != null
                && holder.getLayoutPosition() == 0) {
            ((StaggeredGridLayoutManager.LayoutParams) lp).setFullSpan(true);
        }
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return headerView == null ? position : position - 1;
    }

    public void addHeaderView(View view) {
        this.headerView = view;
    }

    public void addFooterView(View view) {
        this.footerView = view;
    }

    public abstract BaseHolder onCreateHolder(ViewGroup parent, int viewType);
    public abstract void onBindHolder(BaseHolder holder, int position);
    public abstract int getCount();

    public static class BaseHolder extends RecyclerView.ViewHolder {

        public BaseHolder(View itemView) {
            super(itemView);
        }
    }
}
