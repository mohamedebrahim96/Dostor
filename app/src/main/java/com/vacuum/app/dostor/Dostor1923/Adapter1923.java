package com.vacuum.app.dostor.Dostor1923;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vacuum.app.dostor.Model.Item;
import com.vacuum.app.dostor.R;

import java.util.List;

/**
 * Created by Home on 2017-09-09.
 */

public class Adapter1923 extends RecyclerView.Adapter<Adapter1923.MyViewHolder> {

    private List<Item> items;

    public Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mada_title, mada_num;
        public MyViewHolder(View view) {
            super(view);
            mada_title =  view.findViewById(R.id.mada_title);
            mada_num =  view.findViewById(R.id.mada_num);
        }

    }


    public Adapter1923(List<Item> items, Context mContext) {
        this.mContext = mContext;
        this.items = items;
    }

    @Override
    public Adapter1923.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dostor_list_2012, parent, false);

        return new Adapter1923.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Adapter1923.MyViewHolder holder, final int position) {
        /*Typeface Gess_two = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/GE_SS_Two_Bold.otf");*/
        //=======================================================================//
        holder.mada_title.setText(items.get(position).getMada_title());
        holder.mada_num.setText(items.get(position).getMada_num());
        //=========================================
        //holder.mada_num.setTypeface(Gess_two);
        //holder.mada_title.setTypeface(Gess_two);
        //=========================================






    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
