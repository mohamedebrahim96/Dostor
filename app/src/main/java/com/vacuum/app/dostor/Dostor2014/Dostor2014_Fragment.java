package com.vacuum.app.dostor.Dostor2014;

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
 * Created by Home on 2017-08-26.
 */


public class Dostor2014_Fragment extends Fragment {

    RecyclerView recyclerView;
    MainAdapter mainAdapter;

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

        Item movie = new Item("ديباجة الدستور", "http://dostour.eg/2013/intro", R.drawable.design1);
        items.add(movie);

        movie = new Item("الدولة", "http://dostour.eg/2013/topic/country/", R.drawable.design2);
        items.add(movie);

        movie = new Item("المقومات الأساسية للمجتمع", "http://dostour.eg/2013/topic/basic-components/", R.drawable.design3);
        items.add(movie);

        movie = new Item("الحقوق والحريات", "http://dostour.eg/2013/topic/rights-freedoms/", R.drawable.design4);
        items.add(movie);

        movie = new Item("سيادة القانون", "http://dostour.eg/2013/topic/rule-of-law/", R.drawable.design5);
        items.add(movie);

        movie = new Item("نظام الحكم", "http://dostour.eg/2013/topic/regime/", R.drawable.design6);
        items.add(movie);

        movie = new Item("الأحكام العامة و الإنتقالية", "http://dostour.eg/2013/topic/general-transitional/", R.drawable.design7);
        items.add(movie);


        mainAdapter = new MainAdapter(items,this.getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mainAdapter);
    }




}
