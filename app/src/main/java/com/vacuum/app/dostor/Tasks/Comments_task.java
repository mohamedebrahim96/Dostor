package com.vacuum.app.dostor.Tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vacuum.app.dostor.Dostor2014.Adapter;
import com.vacuum.app.dostor.Dostor2014.Comments.Comment_Model;
import com.vacuum.app.dostor.Dostor2014.Comments.Comments_Adapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Home on 10/1/2017.
 */

public class Comments_task  extends AsyncTask<String, Object, List<Comment_Model>> {

    RecyclerView recyclerView;
    List<Comment_Model> items ;
    List<Comment_Model> comments  = new ArrayList<>();
    List<Comment_Model> comments_user_image  = new ArrayList<>();
    List<Comment_Model> username_comment  = new ArrayList<>();
    List<Comment_Model> comment_Date  = new ArrayList<>();
    List<Comment_Model> comment_Like  = new ArrayList<>();
    List<Comment_Model> comment_Dislike  = new ArrayList<>();

    public static int m;
    Context mContext;
    public Comments_task(Context context,RecyclerView recyclerView)
    {
        this.mContext = context;
        this.recyclerView = recyclerView;
    }

    public Comments_task() {

    }


    @Override
    protected List<Comment_Model> doInBackground(String... params) {
        Document doc;
        try {
            doc = Jsoup.connect(params[0]).get();
        Elements links = doc.select("div.row");
        Elements links2 = links.select("div.span9");
        Elements links3 = links2.select("img.loader");
        //Elements more = links2.select("div.more");
        Elements links4 = links2.select("p");


            //System.out.println("links4 : " + links3.text());

            //Elements name = links2.select("div.L_replies_block");
            Elements name = links2.select("div.more");


            /*/for (Element link : name) {
                Elements mmmm = link.select("p");
                System.out.println("===============================================================");
                System.out.println("old  : " + mmmm.text());
                System.out.println("===============================================================");

            }*/


            //================================================================

            Elements dislike = links2.select("small.dislike");
            for(int i = 0; i < dislike.size(); i++) {

                //System.out.println("dislike   : " + link.text());
                //System.out.println("===============================================================");
                Element link = dislike.get(i);
                if(i !=0)
                {
                    comment_Dislike.add(new Comment_Model(link.text()));
                }
            }

            //================================================================
            //================================================================

            Elements likes = links2.select("small.like");

            for(int i = 0; i < likes.size(); i++) {

                //System.out.println("dislike   : " + link.text());
                //System.out.println("===============================================================");
                Element link = likes.get(i);
                if(i !=0)
                {
                    comment_Like.add(new Comment_Model(link.text()));
                }
            }

            //================================================================

            //================================================================

            Elements date = links2.select("span.date");

            for (Element link : date) {
                comment_Date.add(new Comment_Model(link.text()));
            }

            //================================================================

        for (Element link : links3) {
            String x= link.attr("src");
            if(!x.contains("None"))
            {
                //System.out.println("link image : " + "http://dostour.eg"+x);
                comments_user_image.add(new Comment_Model(x));
            }
        }
            //================================================================
        for (int i=0;i<links4.size();i++) {
            //System.out.println("===============================================================");
            String x = links4.get(i).text();
            if(!x.contains("أضف ردك الآن")&&!x.isEmpty())
            {

                if (i>=7)
                {

                    if ( (m % 2) == 0 ){
                        //System.out.println("Number is even "+m+": Username: "+x);
                        username_comment.add(new Comment_Model(x));
                        m++;
                    }else {
                        //System.out.println("Number is odd "+m+": Comment: "+x);
                        comments.add(new Comment_Model(x));
                        m++;
                    }
                }
            }

        }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    protected void onPostExecute(List<Comment_Model> comments) {
        super.onPostExecute(comments);
        System.out.println("comments_user_image.size(): "+comments_user_image.size());
        System.out.println("comments_adapters.size(): "+comments.size());
        System.out.println("username_comment.size(): "+username_comment.size());
        System.out.println("comment_Date.size(): "+comment_Date.size());
        System.out.println("comment_Like.size(): "+comment_Like.size());
        System.out.println("comment_Dislike.size(): "+comment_Dislike.size());

        items =   new ArrayList<>();

         for(int i=0;i<comment_Like.size();i++)
        {
            String U = username_comment.get(i).getComment();
            String C = comments.get(i).getComment();
            String I = comments_user_image.get(i).getComment();
            String D = comment_Date.get(i).getComment();
            String L = comment_Like.get(i).getComment();
            String Dis = comment_Dislike.get(i).getComment();
            items.add(new Comment_Model(U,C,I,D,L,Dis));
        }

        Comments_Adapter mAdapter = new Comments_Adapter(items,mContext);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
}