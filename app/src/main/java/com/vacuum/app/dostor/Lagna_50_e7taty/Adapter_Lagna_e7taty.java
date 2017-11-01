package com.vacuum.app.dostor.Lagna_50_e7taty;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vacuum.app.dostor.Model.Person;
import com.vacuum.app.dostor.R;

import java.util.List;


/**
 * Created by Home on 2017-09-09.
 */

public class Adapter_Lagna_e7taty extends RecyclerView.Adapter<Adapter_Lagna_e7taty.MyViewHolder> {

    private List<Person> persons;

    public Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mada_title, mada_num;
        public ImageView imageView;
        public MyViewHolder(View view) {
            super(view);
            mada_title =  view.findViewById(R.id.mada_title);
            mada_num =  view.findViewById(R.id.mada_num);
            imageView =  view.findViewById(R.id.image);
        }

    }


    public Adapter_Lagna_e7taty(List<Person> persons, Context mContext) {
        this.mContext = mContext;
        this.persons = persons;
    }

    @Override
    public Adapter_Lagna_e7taty.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lagna_50_asasy, parent, false);

        return new Adapter_Lagna_e7taty.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Adapter_Lagna_e7taty.MyViewHolder holder, final int position) {
        Typeface Gess_two = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/GE_SS_Two_Bold.otf");
        //=========================================
        holder.mada_num.setText(persons.get(position).getName());
        holder.mada_title.setText(persons.get(position).getJob());
        Glide.with(mContext)
                .load(persons.get(position).getImageLink())
                .into(holder.imageView);
        //=========================================
        holder.mada_num.setTypeface(Gess_two);
        //holder.mada_title.setTypeface(Gess_two);
        //=========================================

    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

}
