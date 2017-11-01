package com.vacuum.app.dostor.Dostor2014;

import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
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

import com.vacuum.app.dostor.Model.Mada_Realm;
import com.vacuum.app.dostor.Tasks.MyTask;
import com.vacuum.app.dostor.R;

import io.realm.Realm;

/**
 * Created by Home on 2017-08-26.
 */


public class Fragment_2014 extends Fragment {

 RecyclerView recyclerView;
    String url;
    int position;
    View view;
    Realm realm;
    Adapter mAdapter;
    Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        /*if (isNetworkConnected()) {
            new MyTask(this.getContext(),recyclerView).execute();
        } else {
            Toast.makeText(this.getContext(), "error", Toast.LENGTH_SHORT).show();
        }*/
        url = getArguments().getString("url");
        position = getArguments().getInt("position");
        mContext = this.getContext();
        realm = Realm.getDefaultInstance();
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

            }else {
                view = inflater.inflate(R.layout.fragment_two, container, false);
                recyclerView =  view.findViewById(R.id.recycler_view_two);
                //Toast.makeText(view.getContext(), url, Toast.LENGTH_SHORT).show();

                if(realm.where(Mada_Realm.class).equalTo("uid",url).findAll().size() != 0)
                {
                    mAdapter = new Adapter(realm.where(Mada_Realm.class).equalTo("uid",url).findAll(),mContext,recyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                    recyclerView.setAdapter(mAdapter);
                    //Toast.makeText(view.getContext(), "Realm", Toast.LENGTH_SHORT).show();

                }else {
                    new MyTask(mContext, recyclerView).execute(url);
                }

            }
        return view;
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) this.getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

}