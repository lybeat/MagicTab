package com.lybeat.chushoutv.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lybeat.chushoutv.R;
import com.lybeat.chushoutv.adapter.HomeAdapter;
import com.lybeat.chushoutv.entity.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/7/22
 */
public class HomeFragment extends BaseFragment {

    private static final String TAG = "HomeFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 50; ++i) {
            items.add(new Item("Item " + i, "This is the item number " + i, i % 5 == 0));
        }

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new HomeAdapter(getActivity(), items));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        return view;
    }
}
