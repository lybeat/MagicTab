package com.lybeat.chushoutv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lybeat.chushoutv.R;
import com.lybeat.chushoutv.entity.Game;

import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/7/24
 */
public class GameAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "GameAdapter";

    public static final int VIEW_HOLDER_TAG = 0;
    public static final int VIEW_HOLDER_GAME = 1;

    private Context context;
    private List<Game> games;

    public GameAdapter(Context context, List<Game> games) {
        this.context = context;
        this.games = games;
    }

    @Override
    public int getItemViewType(int position) {
        return games.get(position).getTag();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_HOLDER_TAG) {
            return new GameTagHolder(LayoutInflater.from(context).inflate(R.layout.item_game_tag, parent, false));
        } else if (viewType == VIEW_HOLDER_GAME){
            return new GameHolder(LayoutInflater.from(context).inflate(R.layout.item_game, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Game game = games.get(position);
        final ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (holder instanceof GameTagHolder) {
            ((GameTagHolder) holder).setData(game);

            if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams sglp = (StaggeredGridLayoutManager.LayoutParams) lp;
                sglp.setFullSpan(true);
                holder.itemView.setLayoutParams(sglp);
            }
        } else if (holder instanceof GameHolder) {
            ((GameHolder) holder).setData(game);

            if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams sglp = (StaggeredGridLayoutManager.LayoutParams) lp;
                sglp.setFullSpan(false);
                holder.itemView.setLayoutParams(sglp);
            }
        }
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    private class GameHolder extends RecyclerView.ViewHolder {

        TextView titleTxt;
        ImageView iconImg;

        public GameHolder(View itemView) {
            super(itemView);
            titleTxt = (TextView) itemView.findViewById(R.id.game_title);
            iconImg = (ImageView) itemView.findViewById(R.id.game_icon_img);
        }

        public void setData(Game game) {
            titleTxt.setText(game.getTitle());
            iconImg.setImageResource(game.getIconResId());
        }
    }

    private class GameTagHolder extends RecyclerView.ViewHolder {

        TextView tagTxt;
        ImageView tagImg;

        public GameTagHolder(View itemView) {
            super(itemView);
            tagTxt = (TextView) itemView.findViewById(R.id.game_tag_txt);
            tagImg = (ImageView) itemView.findViewById(R.id.game_tag_img);
        }

        public void setData(Game game) {
            tagTxt.setText(game.getTitle());
            tagImg.setImageResource(game.getIconResId());
        }
    }
}
