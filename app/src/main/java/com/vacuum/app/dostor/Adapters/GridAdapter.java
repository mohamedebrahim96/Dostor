package com.vacuum.app.dostor.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vacuum.app.dostor.Dostor1882.Dostor1882_Fragment;
import com.vacuum.app.dostor.Dostor1882.Fragment_1882;
import com.vacuum.app.dostor.Dostor1923.Dostor1923_Fragment;
import com.vacuum.app.dostor.Dostor1930.Dostor1930_Fragment;
import com.vacuum.app.dostor.Dostor1954.Dostor1954_Fragment;
import com.vacuum.app.dostor.Dostor1971.Dostor1971_Fragment;
import com.vacuum.app.dostor.Dostor2007.Dostor2007_Fragment;
import com.vacuum.app.dostor.Dostor2012.Dostor2012_Fragment;
import com.vacuum.app.dostor.Dostor2014.Dostor2014_Fragment;
import com.vacuum.app.dostor.Lagna_50_asasy.Fragment_Lagna_asasy;
import com.vacuum.app.dostor.Lagna_50_e7taty.Fragment_Lagna_e7taty;
import com.vacuum.app.dostor.Model.Item;
import com.vacuum.app.dostor.Model.Person;
import com.vacuum.app.dostor.R;

import java.util.List;

import io.realm.Realm;

/**
 * Created by Home on 2017-08-29.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.MyViewHolder> {

    private List<Item> items;
    Realm realm;
    private Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;
        public Button circle;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            image = (ImageView) view.findViewById(R.id.image);
            circle = (Button) view.findViewById(R.id.circle);

        }

    }


    public GridAdapter(List<Item> items, Context mContext) {
        this.items = items;
        this.mContext = mContext;
        realm =  Realm.getDefaultInstance();

    }

    @Override
    public GridAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_layout, parent, false);

        return new GridAdapter.MyViewHolder(itemView);
    }





    @Override
    public void onBindViewHolder(GridAdapter.MyViewHolder holder, final int position) {
        Typeface Gess_two = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/GE_SS_Two_Bold.otf");

        holder.title.setText(items.get(position).getMada_title());
        holder.image.setImageResource(items.get(position).getImage());

        //=========================================
        holder.title.setTypeface(Gess_two);
        //=========================================
        //if(position == items.size()-2|| position == items.size()-1)
        if(realm.where(Person.class).equalTo("tableAndId","e7taty").findAll().size() == 0&&position == items.size()-1)
        {
            holder.circle.setVisibility(View.VISIBLE);
        }
        if(realm.where(Person.class).equalTo("tableAndId","asasy").findAll().size() == 0&&position == items.size()-2)
        {
            holder.circle.setVisibility(View.VISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(position == 0)
                {
                    Dostor2014_Fragment Fragment_2014 = new Dostor2014_Fragment();
                    ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_left,R.anim.slide_out_right)
                            .replace(R.id.container, Fragment_2014,"home")
                            .addToBackStack("home")
                            .commit();
                }else if(position == 1)
                {
                    Dostor2012_Fragment Fragment_2012 = new Dostor2012_Fragment();
                    ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_left,R.anim.slide_out_right)
                            .replace(R.id.container, Fragment_2012,"home")
                            .addToBackStack("home")
                            .commit();
                }else if(position == 2)
                {
                    Dostor2007_Fragment Fragment_2007 = new Dostor2007_Fragment();
                    ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_left,R.anim.slide_out_right)
                            .replace(R.id.container, Fragment_2007,"home")
                            .addToBackStack("home")
                            .commit();
                }else if(position == 3)
                {
                    Dostor1971_Fragment Fragment_1971 = new Dostor1971_Fragment();
                    ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_left,R.anim.slide_out_right)
                            .replace(R.id.container, Fragment_1971,"home")
                            .addToBackStack("home")
                            .commit();
                }else if(position == 4)
                {
                    Dostor1954_Fragment Fragment_1954 = new Dostor1954_Fragment();
                    ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_left,R.anim.slide_out_right)
                            .replace(R.id.container, Fragment_1954,"home")
                            .addToBackStack("home")
                            .commit();
                }else if(position == 5)
                {
                    Dostor1930_Fragment Fragment_1930 = new Dostor1930_Fragment();
                    ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_left,R.anim.slide_out_right)
                            .replace(R.id.container, Fragment_1930,"home")
                            .addToBackStack("home")
                            .commit();
                }else if(position == 6)
                {
                    Dostor1923_Fragment Fragment_1923 = new Dostor1923_Fragment();
                    ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_left,R.anim.slide_out_right)
                            .replace(R.id.container, Fragment_1923,"home")
                            .addToBackStack("home")
                            .commit();
                }else if(position == 7)
                {
                    Fragment_1882 Fragment_1882 = new Fragment_1882();
                    ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_left,R.anim.slide_out_right)
                            .replace(R.id.container, Fragment_1882,"home")
                            .addToBackStack("home")
                            .commit();
                }else if(position == 8)
                {
                    Fragment_Lagna_asasy lagna_asasy = new Fragment_Lagna_asasy();
                    ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_left,R.anim.slide_out_right)
                            .replace(R.id.container, lagna_asasy,"home")
                            .addToBackStack("home")
                            .commit();
                }else if(position == 9)
                {
                    Fragment_Lagna_e7taty lagna_e7taty = new Fragment_Lagna_e7taty();
                    ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_left,R.anim.slide_out_right)
                            .replace(R.id.container, lagna_e7taty,"home")
                            .addToBackStack("home")
                            .commit();
                }
                else {
                    Toast.makeText(mContext, "null", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
