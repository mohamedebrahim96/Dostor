package com.vacuum.app.dostor.NavigationDrawer;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vacuum.app.dostor.R;

/**
 * Created by Home on 10/19/2017.
 */

public class FavoriteFragment extends Fragment {

    Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.favoritefragment, container, false);
        mContext = this.getActivity();

        return view;
    }
}