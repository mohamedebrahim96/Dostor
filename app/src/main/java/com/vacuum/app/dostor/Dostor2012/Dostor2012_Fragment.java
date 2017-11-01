package com.vacuum.app.dostor.Dostor2012;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vacuum.app.dostor.Model.Item;
import com.vacuum.app.dostor.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 2017-09-09.
 */

public class Dostor2012_Fragment extends Fragment {

    RecyclerView recyclerView;
    Adapter_2012 adapter_2012;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_one);


        prepareMovieData();
        return view;
    }

    private void prepareMovieData() {
        List<Item> items = new ArrayList<>();

        Item movie = new Item("ديباجة الدستور",  R.drawable.design1);
        items.add(movie);

        movie = new Item("مقومات الدولة والمجتمع",  R.drawable.design2);
        items.add(movie);

        movie = new Item("الحقوق والحريات",  R.drawable.design3);
        items.add(movie);

        movie = new Item("السلطات العامة",  R.drawable.design4);
        items.add(movie);

        movie = new Item("الهيئات المستقلة والأجهزة الرقابية", R.drawable.design5);
        items.add(movie);

        movie = new Item("الأحكام الختامية والانتقالية",  R.drawable.design6);
        items.add(movie);


        adapter_2012 = new Adapter_2012(items, this.getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter_2012);
    }
}
