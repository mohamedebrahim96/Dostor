package com.vacuum.app.dostor.Dostor2014.Comments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.vacuum.app.dostor.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by Home on 10/3/2017.
 */

public class Comments_Adapter extends RecyclerView.Adapter<Comments_Adapter.MyViewHolder> {


    private List<Comment_Model> items;

    Context mContext;
    public static int  m = 0;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView username, comment_Date, comment,like,dis_like;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            username = (TextView) view.findViewById(R.id.username);
            comment = (TextView) view.findViewById(R.id.comment);
            comment_Date = (TextView) view.findViewById(R.id.comment_Date);
            like = (TextView) view.findViewById(R.id.like);
            dis_like = (TextView) view.findViewById(R.id.dis_like);
            imageView = (ImageView) view.findViewById(R.id.imageView);

        }
    }


    public Comments_Adapter(List<Comment_Model> items,Context mContext) {
        this.items = items;
        this.mContext = mContext;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_recycler_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        Comment_Model model = items.get(position);
        System.out.println("http://dostour.eg"+model.getUser_image()+"======");
        holder.username.setText(model.getUser_name());
        holder.comment.setText(model.getComment());
        holder.comment_Date.setText(model.getComment_Date());
        holder.like.setText(model.getLikes());
        holder.dis_like.setText(model.getDislikes());
        //new DownloadImageTask(holder.imageView).execute("http://dostour.eg"+model.getUser_image());
        Glide.with(mContext)
                .load("http://dostour.eg"+model.getUser_image())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ///Bitmap decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(stream.toByteArray()));

            result.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Glide.with(mContext)
                    .load(stream.toByteArray())
                    .into(bmImage);
            //bmImage.setImageBitmap(result);
        }
    }


}