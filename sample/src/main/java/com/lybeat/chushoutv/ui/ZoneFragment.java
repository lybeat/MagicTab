package com.lybeat.chushoutv.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lybeat.chushoutv.R;
import com.lybeat.chushoutv.adapter.GameAdapter;
import com.lybeat.chushoutv.entity.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/7/22
 */
public class ZoneFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zone, container, false);

        List<Game> games = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            if (i == 0) {
                games.add(new Game("常用专区", R.drawable.small_adapter_common, GameAdapter.VIEW_HOLDER_TAG));
            } else if (i == 4) {
                games.add(new Game("全部专区", R.drawable.small_adapter_all, GameAdapter.VIEW_HOLDER_TAG));
            } else {
                games.add(new Game("百万亚瑟王" + i, R.drawable.game_icon, GameAdapter.VIEW_HOLDER_GAME));
            }
        }
        GameAdapter gameAdapter = new GameAdapter(getActivity(), games);
        RecyclerView zoneRecycler = (RecyclerView) view.findViewById(R.id.zone_recycler);
        zoneRecycler.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        zoneRecycler.setAdapter(gameAdapter);

        return view;
    }
}
