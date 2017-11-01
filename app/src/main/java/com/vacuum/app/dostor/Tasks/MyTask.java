package com.vacuum.app.dostor.Tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vacuum.app.dostor.Dostor2014.Adapter;
import com.vacuum.app.dostor.Model.Mada_Realm;
import com.vacuum.app.dostor.Model.Person;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by Home on 2017-08-24.
 */

public class MyTask extends AsyncTask<String, Object, List<String>> {

    RecyclerView recyclerView;
    Adapter mAdapter;
    List<String> items_mada_num  = new ArrayList<>();
    List<String> items_update  = new ArrayList<>();
    List<String> items_mada  = new ArrayList<>();
    List<String> items_old  = new ArrayList<>();
    List<String> comments_links  = new ArrayList<>();
    List<Mada_Realm> Mada = new ArrayList<>();
    Realm realm;
    Context mContext;
    String url;
    public MyTask(Context context,RecyclerView recyclerView)
    {
        this.mContext = context;
        this.recyclerView = recyclerView;

    }
    @Override
    protected List<String> doInBackground(String... params) {
        Document doc;
        try {
            url = params[0];
            doc = Jsoup.connect(params[0]).get();
            //doc = Jsoup.connect("http://dostour.eg/2013/topic/regime/").get();
            //doc = Jsoup.connect("http://dostour.eg/2013/topic/country/").get();

            Elements links = doc.select("div.row");
            Elements links2 = links.select("div.span9");
            Elements links3 = links2.select("div.topic");
            Elements links4 = links3.select("p");


            Elements comment  = links3.select("a.comments-number");

            //=====================================================
            for (int i=0;i<links3.size();i++)
            {
                Element cc = links3.get(i);
                Elements mm = cc.select("div.editings-con");
                Elements href2 = mm.select("a[href]");
                if(mm.isEmpty())
                {
                    //System.out.println("isEmpty()");
                    items_old.add("isEmpty()");
                }else {
                    Element link = href2.first();
                    //System.out.println("http://dostour.eg"+link.attr("href"));
                    items_old.add("http://dostour.eg"+link.attr("href"));
                }

            }

            //=====================================================

            for (Element link : comment) {

                comments_links.add(link.attr("abs:href"));
                //System.out.println("Comment link : " + link.attr("abs:href"));
                //System.out.println("================================================================");
            }
            //================================================================




            for(int i=0;i<links4.size();i++) {
                Element author = links4.get(i);
                if (author.text().length() != 0) {
                    if (author.text().contains("مادة (")) {
                        if(author.text().contains("المادة (102)") || author.text().contains("المادة (180)"))
                        {
                            System.out.println((i-2)/5+": "+author.text() + "\n\n");
                            items_mada.add(author.text());
                        }
                        else
                            {
                                items_mada_num.add(author.text());
                                //System.out.println(i/5+": "+author.text() + "\n\n");}
                            }
                    }else if (author.text().contains("آخر تحديث")) {
                        items_update.add(author.text());
                        //System.out.println((i-4)/5+": "+author.text() + "\n\n");
                    }
                    else {
                        //System.out.println((i-2)/5+": "+author.text() + "\n\n");
                        items_mada.add(author.text());
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return items_mada;
    }


    @Override
    protected void onPostExecute(List<String> items) {

        System.out.println("items.size(): "+items.size());
        System.out.println("items_mada.size(): "+items_mada.size());
        System.out.println("items_mada_num.size(): "+items_mada_num.size());
        System.out.println("items_update.size(): "+items_update.size());
        System.out.println("items_old.size(): "+items_old.size());

        for(int i=0;i<items.size()-1;i++)
        {
            int x = items_mada_num.get(i).indexOf(")");
            final String comment = items_mada_num.get(i).substring(x+1,items_mada_num.get(i).length());
            final String mada_num = items_mada_num.get(i).substring(0,x+1);
            final String mada_title = items_mada.get(i);
            final String mada_update = items_update.get(i);
            final String mada_comments_links = comments_links.get(i);
            final String mada_items_old = items_old.get(i);

            Mada.add(new Mada_Realm(mada_num,mada_title,comment,mada_update,mada_comments_links,mada_items_old));
        }

        realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                for (Mada_Realm x:Mada)
                {
                    Mada_Realm mada = realm.createObject(Mada_Realm.class);
                    mada.setMada_num(x.getMada_num());
                    mada.setMada_title(x.getMada_title());
                    mada.setMada_update(x.getMada_update());
                    mada.setComments(x.getComments());
                    mada.setComments_links(x.getComments_links());
                    mada.setMada_old_links(x.getMada_old_links());
                    mada.setUid(url);
                }
            }
        });

        mAdapter = new Adapter(Mada,mContext,recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public void clearRealm()
    {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(Mada_Realm.class);
            }
        });
    }


}