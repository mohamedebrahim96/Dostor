package com.vacuum.app.dostor.Dostor1882;

import android.content.Context;
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

public class Fragment_1882 extends Fragment {

    RecyclerView recyclerView;
    View view;
    Context mContext;
    Adapter1882 adapter2012;
    List<Item> items;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_two, container, false);
        recyclerView =  view.findViewById(R.id.recycler_view_two);
        section_1();
        return view;
    }
    private void section_1() {
        items = new ArrayList<>();
        String x = getResources().getString(R.string.section_1_1882);
        String mada = " مادة ";
        for(String word: x.split(mada))
        {
            items.add(new Item("مادة "+word.substring(0,2),word.substring(2,word.length())));
        }
        make_adapter();
    }
    private void make_adapter() {
        adapter2012 = new Adapter1882(items,mContext);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter2012);
        adapter2012.notifyDataSetChanged();
    }

}