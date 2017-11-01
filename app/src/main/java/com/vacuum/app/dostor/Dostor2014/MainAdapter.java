package com.vacuum.app.dostor.Dostor2014;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vacuum.app.dostor.Model.Item;
import com.vacuum.app.dostor.R;

import java.util.List;

/**
 * Created by Home on 2017-08-24.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    private List<Item> items;

    private Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            image = (ImageView) view.findViewById(R.id.image);

        }

    }


    public MainAdapter(List<Item> items, Context mContext) {
        this.items = items;
        this.mContext = mContext;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_list, parent, false);

        return new MyViewHolder(itemView);
    }





    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Typeface sky = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/sky.ttf");
        Typeface dubai = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/dubai.ttf");
        Typeface Gess_two = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/GE_SS_Two_Bold.otf");

        holder.title.setText(items.get(position).getMada_title());
        holder.image.setImageResource(items.get(position).getImage());

        //=========================================
        holder.title.setTypeface(Gess_two);
        //=========================================

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment_2014 twoFragment = new Fragment_2014();
                Bundle args = new Bundle();

                args.putInt("position",position);
                args.putString("url", items.get(position).getMada_num());
                twoFragment.setArguments(args);
                ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_left,R.anim.slide_out_right)
                            .replace(R.id.container, twoFragment)
                            .addToBackStack(null)
                            .commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
