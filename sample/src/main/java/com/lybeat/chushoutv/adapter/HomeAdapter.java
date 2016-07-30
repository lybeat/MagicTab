package com.lybeat.chushoutv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lybeat.chushoutv.entity.Item;
import com.lybeat.chushoutv.R;

import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/7/23
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private static final String TAG = "HomeAdapter";

    private static final int TYPE_INACTIVE = 0;
    private static final int TYPE_ACTIVE = 1;

    private Context context;
    private List<Item> items;

    public HomeAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = viewType == TYPE_INACTIVE ? R.layout.item_inactive : R.layout.item_active;

        View view = LayoutInflater.from(context).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).isActive() ? TYPE_INACTIVE : TYPE_ACTIVE;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Item item = items.get(position);

        holder.titleTxt.setText(item.getTitle());
        holder.subtitleTxt.setText(item.getSubtitle() + ", which is " + (item.isActive() ? "active" : "inactive"));

        final ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams sglp = (StaggeredGridLayoutManager.LayoutParams) lp;
            sglp.setFullSpan(item.isActive());
            holder.itemView.setLayoutParams(sglp);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener, View.OnLongClickListener {

        TextView titleTxt;
        TextView subtitleTxt;

        public ViewHolder(View itemView) {
            super(itemView);

            titleTxt = (TextView) itemView.findViewById(R.id.title_txt);
            subtitleTxt = (TextView) itemView.findViewById(R.id.subtitle_txt);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.i(TAG, "onClick position: " + getLayoutPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            Log.i(TAG, "onLongClick position: " + getLayoutPosition());
            return false;
        }
    }
}
