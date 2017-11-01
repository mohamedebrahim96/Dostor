package com.vacuum.app.dostor.Dostor1954;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Home on 2017-09-09.
 */

public class Fragment_1954 extends Fragment {

    RecyclerView recyclerView;
    int position;
    View view;
    Context mContext;
    Adapter1954 adapter2012;
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
            view = inflater.inflate(R.layout.fragment_two, container, false);
            recyclerView =  view.findViewById(R.id.recycler_view_two);
            section_1();
        }else if (position == 1){
            view = inflater.inflate(R.layout.fragment_two, container, false);
            recyclerView =  view.findViewById(R.id.recycler_view_two);
            section_2();

        }else if (position == 2){
            view = inflater.inflate(R.layout.fragment_two, container, false);
            recyclerView =  view.findViewById(R.id.recycler_view_two);
            section_3();
        }else if (position == 3){
            view = inflater.inflate(R.layout.fragment_two, container, false);
            recyclerView =  view.findViewById(R.id.recycler_view_two);
            section_4();
        }else if (position == 4){
            view = inflater.inflate(R.layout.fragment_two, container, false);
            recyclerView =  view.findViewById(R.id.recycler_view_two);
            section_5();
        }else if (position == 5){
            view = inflater.inflate(R.layout.fragment_two, container, false);
            recyclerView =  view.findViewById(R.id.recycler_view_two);
            section_6();
        }else if (position == 6){
            view = inflater.inflate(R.layout.fragment_two, container, false);
            recyclerView =  view.findViewById(R.id.recycler_view_two);
            section_7();
        }else if (position == 7){
            view = inflater.inflate(R.layout.fragment_two, container, false);
            recyclerView =  view.findViewById(R.id.recycler_view_two);
            section_8();
        }else if (position == 8){
            view = inflater.inflate(R.layout.fragment_two, container, false);
            recyclerView =  view.findViewById(R.id.recycler_view_two);
            section_9();
        }else if (position == 9){
            view = inflater.inflate(R.layout.fragment_two, container, false);
            recyclerView =  view.findViewById(R.id.recycler_view_two);
            section_10();
        }
        return view;
    }



    private void section_1() {
        items = new ArrayList<>();
        String x = getResources().getString(R.string.section_1_1954);
        String mada = " مادة ";

        for(String word: x.split(mada))
        {
            items.add(new Item("مادة "+word.substring(0,2),word.substring(2,word.length())));
        }
        make_adapter();

    }
    private void  section_2() {
        items = new ArrayList<>();
        String x = getResources().getString(R.string.section_2_1954);
        String mada = " مادة ";
        for(String word: x.split(mada))
        {
            if(word.contains("[عدل]")){
                Pattern p = Pattern.compile(Pattern.quote("[عدل]"));
                Matcher m = p.matcher(word);
                StringBuffer sb = new StringBuffer();
                while (m.find()) {
                    m.appendReplacement(sb, "");
                }
                String word2 = m.replaceAll("");
                System.out.println(word2);

                items.add(new Item("مادة "+word2.substring(0,3),word2.substring(3,word2.length())));
            }

        }
        make_adapter();
    }

    private void section_3() {
        items = new ArrayList<>();
        String x = getResources().getString(R.string.section_3_1954);
        String mada = " مادة ";

        for(String word: x.split(mada))
        {
            if(word.contains("[عدل]")){
                Pattern p = Pattern.compile(Pattern.quote("[عدل]"));
                Matcher m = p.matcher(word);
                StringBuffer sb = new StringBuffer();
                while (m.find()) {
                    m.appendReplacement(sb, "");
                }
                String word2 = m.replaceAll("");
                System.out.println(word2);

                items.add(new Item("مادة "+word2.substring(0,3),word2.substring(3,word2.length())));
            }

        }
        make_adapter();
    }

    private void section_4() {
        items = new ArrayList<>();
        String x = getResources().getString(R.string.section_4_1954);
        String mada = " مادة ";

        for(String word: x.split(mada))
        {
            if(word.contains("[عدل]")){
                Pattern p = Pattern.compile(Pattern.quote("[عدل]"));
                Matcher m = p.matcher(word);
                StringBuffer sb = new StringBuffer();
                while (m.find()) {
                    m.appendReplacement(sb, "");
                }
                String word2 = m.replaceAll("");
                System.out.println(word2);

                items.add(new Item("مادة "+word2.substring(0,3),word2.substring(3,word2.length())));
            }

        }
        make_adapter();
    }
    private void section_5() {
        items = new ArrayList<>();
        String x = getResources().getString(R.string.section_5_1954);
        String mada = " مادة ";

        for(String word: x.split(mada))
        {
            if(word.contains("[عدل]")){
                Pattern p = Pattern.compile(Pattern.quote("[عدل]"));
                Matcher m = p.matcher(word);
                StringBuffer sb = new StringBuffer();
                while (m.find()) {
                    m.appendReplacement(sb, "");
                }
                String word2 = m.replaceAll("");
                System.out.println(word2);

                items.add(new Item("مادة "+word2.substring(0,3),word2.substring(3,word2.length())));
            }

        }
        make_adapter();
    }
    private void section_6() {
        items = new ArrayList<>();
        String x = getResources().getString(R.string.section_6_1954);
        String mada = " مادة ";

        for(String word: x.split(mada))
        {
            if(word.contains("[عدل]")){
                Pattern p = Pattern.compile(Pattern.quote("[عدل]"));
                Matcher m = p.matcher(word);
                StringBuffer sb = new StringBuffer();
                while (m.find()) {
                    m.appendReplacement(sb, "");
                }
                String word2 = m.replaceAll("");
                System.out.println(word2);

                items.add(new Item("مادة "+word2.substring(0,3),word2.substring(3,word2.length())));
            }

        }
        make_adapter();
    }
    private void section_7() {
        items = new ArrayList<>();
        String x = getResources().getString(R.string.section_7_1954);
        String mada = " مادة ";

        for(String word: x.split(mada))
        {
            if(word.contains("[عدل]")){
                Pattern p = Pattern.compile(Pattern.quote("[عدل]"));
                Matcher m = p.matcher(word);
                StringBuffer sb = new StringBuffer();
                while (m.find()) {
                    m.appendReplacement(sb, "");
                }
                String word2 = m.replaceAll("");
                System.out.println(word2);

                items.add(new Item("مادة "+word2.substring(0,3),word2.substring(3,word2.length())));
            }

        }
        make_adapter();
    }



    private void section_8() {
        items = new ArrayList<>();
        String x = getResources().getString(R.string.section_8_1954);
        String mada = " مادة ";
        for(String word: x.split(mada))
        {
            if(word.contains("[عدل]")){
                Pattern p = Pattern.compile(Pattern.quote("[عدل]"));
                Matcher m = p.matcher(word);
                StringBuffer sb = new StringBuffer();
                while (m.find()) {
                    m.appendReplacement(sb, "");
                }
                String word2 = m.replaceAll("");
                System.out.println(word2);

                items.add(new Item("مادة "+word2.substring(0,3),word2.substring(3,word2.length())));
            }

        }
        make_adapter();
    }
    private void section_9() {
        items = new ArrayList<>();
        String x = getResources().getString(R.string.section_9_1954);
        String mada = " مادة ";

        for(String word: x.split(mada))
        {
                items.add(new Item("مادة "+word.substring(0,3),word.substring(3,word.length())));
        }
        make_adapter();
    }
    private void section_10() {
        items = new ArrayList<>();
        String x = getResources().getString(R.string.section_10_1954);
        String mada = " مادة ";

        for(String word: x.split(mada))
        {
            if(word.contains("[عدل]")){
                Pattern p = Pattern.compile(Pattern.quote("[عدل]"));
                Matcher m = p.matcher(word);
                StringBuffer sb = new StringBuffer();
                while (m.find()) {
                    m.appendReplacement(sb, "");
                }
                String word2 = m.replaceAll("");
                System.out.println(word2);

                items.add(new Item("مادة "+word2.substring(0,3),word2.substring(3,word2.length())));
            }

        }
        make_adapter();
    }
    private void make_adapter() {
        adapter2012 = new Adapter1954(items,mContext);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter2012);
        adapter2012.notifyDataSetChanged();
    }

}