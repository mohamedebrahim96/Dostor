package com.vacuum.app.dostor.Dostor1930;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.vacuum.app.dostor.Model.Item;
import com.vacuum.app.dostor.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 2017-09-09.
 */

public class Fragment_1930 extends Fragment {

    RecyclerView recyclerView;
    int position;
    View view;
    Context mContext;
    Adapter1930 adapter2012;
    List<Item> items;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        /*if (isNetworkConnected()) {
            new MyTask(this.getContext(),recyclerView).execute();
        } else {
            Toast.makeText(this.getContext(), "error", Toast.LENGTH_SHORT).show();
        }*/
        position = getArguments().getInt("position");
        if(position == 0)
        {
            view = inflater.inflate(R.layout.intro_layout, container, false);
            Toast.makeText(view.getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
            TextView intro_1 = view.findViewById(R.id.intro_1);
            TextView intro_2 = view.findViewById(R.id.intro_2);
            //===============================================================
            Typeface sky = Typeface.createFromAsset(view.getContext().getAssets(),
                    "fonts/sky.ttf");
            Typeface Gess_two = Typeface.createFromAsset(view.getContext().getAssets(),
                    "fonts/GE_SS_Two_Bold.otf");
            //==========================================================================
            intro_1.setTypeface(sky);
            intro_2.setTypeface(Gess_two);
            intro_2.setText(R.string.intro_1930);
        }else if (position == 1){
            view = inflater.inflate(R.layout.fragment_two, container, false);
            recyclerView =  view.findViewById(R.id.recycler_view_two);
            section_1();

        }else if (position == 2){
            view = inflater.inflate(R.layout.fragment_two, container, false);
            recyclerView =  view.findViewById(R.id.recycler_view_two);
            section_2();
        }else if (position == 3){
            view = inflater.inflate(R.layout.fragment_two, container, false);
            recyclerView =  view.findViewById(R.id.recycler_view_two);
            section_3();
        }else if (position == 4){
            view = inflater.inflate(R.layout.fragment_two, container, false);
            recyclerView =  view.findViewById(R.id.recycler_view_two);
            section_4();
        }else if (position == 5){
            view = inflater.inflate(R.layout.fragment_two, container, false);
            recyclerView =  view.findViewById(R.id.recycler_view_two);
            section_5();
        }else if (position == 6){
            view = inflater.inflate(R.layout.fragment_two, container, false);
            recyclerView =  view.findViewById(R.id.recycler_view_two);
            section_6();
        }else if (position == 7){
            view = inflater.inflate(R.layout.fragment_two, container, false);
            recyclerView =  view.findViewById(R.id.recycler_view_two);
            section_7();
        }
        return view;
    }



    private void section_1() {
        items = new ArrayList<>();
        String x = getResources().getString(R.string.section_1_1930);
        String mada = " مادة ";

        for(String word: x.split(mada))
        {
            items.add(new Item("مادة "+word.substring(0,2),word.substring(2,word.length())));
        }
        make_adapter();

    }
    private void  section_2() {
        items = new ArrayList<>();
        String x = getResources().getString(R.string.section_2_1930);
        String mada = " مادة ";

        for(String word: x.split(mada))
        {
            items.add(new Item("مادة "+word.substring(0,2),word.substring(2,word.length())));
        }
        make_adapter();
    }

    private void section_3() {
        items = new ArrayList<>();
        String x = getResources().getString(R.string.section_3_1930);
        String mada = " مادة ";

        for(String word: x.split(mada))
        {
            items.add(new Item("مادة "+word.substring(0,3),word.substring(3,word.length())));
        }
        make_adapter();
    }

    private void section_4() {
        items = new ArrayList<>();
        String x = getResources().getString(R.string.section_4_1930);
        String mada = " مادة ";

        for(String word: x.split(mada))
        {
            items.add(new Item("مادة "+word.substring(0,3),word.substring(3,word.length())));
        }
        make_adapter();
    }
    private void section_5() {
        items = new ArrayList<>();
        String x = getResources().getString(R.string.section_5_1930);
        String mada = " مادة ";

        for(String word: x.split(mada))
        {
            items.add(new Item("مادة "+word.substring(0,3),word.substring(3,word.length())));
        }
        make_adapter();
    }
    private void section_6() {
        items = new ArrayList<>();
        String x = getResources().getString(R.string.section_6_1930);
        String mada = " مادة ";

        for(String word: x.split(mada))
        {
            items.add(new Item("مادة "+word.substring(0,3),word.substring(3,word.length())));
        }
        make_adapter();
    }
    private void section_7() {
        items = new ArrayList<>();
        String x = getResources().getString(R.string.section_7_1930);
        String mada = " مادة ";

        for(String word: x.split(mada))
        {
            items.add(new Item("مادة "+word.substring(0,3),word.substring(3,word.length())));
        }
        make_adapter();
    }

    private void make_adapter() {
        adapter2012 = new Adapter1930(items,mContext);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter2012);
        adapter2012.notifyDataSetChanged();
    }

}