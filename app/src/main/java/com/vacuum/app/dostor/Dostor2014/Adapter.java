package com.vacuum.app.dostor.Dostor2014;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vacuum.app.dostor.Dostor2014.Comments.Comments;
import com.vacuum.app.dostor.Model.Item;
import com.vacuum.app.dostor.Model.Mada_Realm;
import com.vacuum.app.dostor.Model.Person;
import com.vacuum.app.dostor.R;
import com.vacuum.app.dostor.Tasks.oldArticles;

import java.util.List;

import io.realm.Realm;

/**
 * Created by Home on 2017-08-23.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Mada_Realm> Mada;

    private Context mContext;
    public static int expandedPosition = -1;
     RecyclerView recyclerView;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mada_title, mada_num, update,comments,old_update,old_title;
        public LinearLayout llExpandArea;
        CardView Btn_Comment;
        public MyViewHolder(View view) {
            super(view);
            mada_title =  view.findViewById(R.id.mada_title);
            mada_num =  view.findViewById(R.id.mada_num);
            update =  view.findViewById(R.id.update);
            comments =  view.findViewById(R.id.comments);
            llExpandArea =  view.findViewById(R.id.llExpandArea);
            old_update =  view.findViewById(R.id.old_update);
            old_title =  view.findViewById(R.id.old_title);
            Btn_Comment =  view.findViewById(R.id.Btn_Comment);

        }

    }


    public Adapter(List<Mada_Realm> Mada,Context mContext,RecyclerView recyclerView) {
        this.Mada = Mada;
        this.mContext = mContext;
        this.recyclerView = recyclerView;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Typeface Gess_two = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/GE_SS_Two_Bold.otf");
        //=======================================================================//
        //int x = items_mada_num.get(position).indexOf(")");
        //final String comment = items_mada_num.get(position).substring(x+1,items_mada_num.get(position).length());
        //final String mada_num = items_mada_num.get(position).substring(0,x+1);
        holder.mada_title.setText(Mada.get(position).getMada_title());
        holder.mada_num.setText(Mada.get(position).getMada_num());
        holder.update.setText(Mada.get(position).getMada_update());
        holder.comments.setText(Mada.get(position).getComments());
        //=========================================
        holder.mada_num.setTypeface(Gess_two);
        holder.mada_title.setTypeface(Gess_two);
        holder.comments.setTypeface(Gess_two);
        //=========================================

        //holder.llExpandArea.setVisibility(View.GONE);
        if (position == expandedPosition) {
            holder.llExpandArea.setVisibility(View.VISIBLE);
        } else {
            holder.llExpandArea.setVisibility(View.GONE);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //=============================================================
                boolean shouldExpand = holder.llExpandArea.getVisibility() == View.GONE;

                ChangeBounds transition = new ChangeBounds();
                transition.setDuration(500);

                if (shouldExpand) {
                    holder.llExpandArea.setVisibility(View.VISIBLE);
                    expandedPosition = position;
                    try {
                        String full_Title = new oldArticles().execute(Mada.get(position).getMada_old_links()).get().get(0);
                        String old_Title = new oldArticles().execute(Mada.get(position).getMada_old_links()).get().get(1);
                        String old_Update = new oldArticles().execute(Mada.get(position).getMada_old_links()).get().get(2);

                        holder.mada_title.setText(full_Title);
                        holder.old_title.setText(old_Title);
                        holder.old_update.setText(old_Update);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    holder.llExpandArea.setVisibility(View.GONE);
                    holder.mada_title.setText(Mada.get(position).getMada_title());
                }

                TransitionManager.beginDelayedTransition(recyclerView, transition);
                holder.itemView.setActivated(shouldExpand);
                //=============================================================

            }
        });

        holder.Btn_Comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(mContext, Comments.class);
                myIntent.putExtra("comment_link",Mada.get(position).getComments_links());
                mContext.startActivity(myIntent);
                //Toast.makeText(mContext, Mada.get(position).getComments_links(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return Mada.size();
    }






}
