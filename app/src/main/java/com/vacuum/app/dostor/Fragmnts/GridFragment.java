package com.vacuum.app.dostor.Fragmnts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vacuum.app.dostor.Adapters.GridAdapter;
import com.vacuum.app.dostor.Model.Item;
import com.vacuum.app.dostor.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 2017-08-29.
 */

public class GridFragment extends Fragment {
    RecyclerView recyclerView;
    GridAdapter countryAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_one);



        prepareMovieData2();
        return view;
    }

    private void prepareMovieData2() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("دستور مصر 2014",  R.drawable.egypt_flag));
        items.add(new Item("دستور مصر 2012",  R.drawable.egypt_flag));
        items.add(new Item("دستور مصر 2007", R.drawable.egypt_flag));
        items.add(new Item("دستور مصر 1971", R.drawable.egypt_flag));
        items.add(new Item("دستور مصر 1954",  R.drawable.egypt_flag));
        items.add(new Item("دستور مصر 1930",  R.drawable.egypt_flag));
        items.add(new Item("دستور مصر 1923",  R.drawable.egypt_flag));
        items.add(new Item("دستور مصر 1882",  R.drawable.egypt_flag));
        items.add(new Item("أعضاء لجنة الخمسين الأساسيون" ,R.drawable.design5));
        items.add(new Item("أعضاء لجنة الخمسين الإحتياطيون", R.drawable.design4));


        recyclerView.setHasFixedSize(true);
        countryAdapter = new GridAdapter(items,this.getActivity());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.getActivity(),3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(countryAdapter);
    }

}
