package com.vacuum.app.dostor.Tasks;

import android.content.Context;
import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


/**
 * Created by Home on 2017-08-24.
 */

public class oldArticles extends AsyncTask<String, Object, List<String> > {


    Context mContext;
    public oldArticles(Context context)
    {
        this.mContext = context;

    }

    public oldArticles() {

    }


    @Override
    protected List<String> doInBackground(String... params) {
        Document doc;
        List<String> old = new ArrayList<>();
        try {
            doc = Jsoup.connect(params[0]).get();
            //doc = Jsoup.connect("http://dostour.eg/2013/topic/regime/").get();
            //doc = Jsoup.connect("http://dostour.eg/2013/article_versions/state-1-5/").get();

            Elements links = doc.select("div.row");
            Elements links2 = links.select("div.span9");
            Elements links3 = links2.select("div.well");
            Elements links4 = links3.select("span.edit-date");



            Elements title = links3.select("div#version-67");
            //System.out.println("//===============================================");
            //System.out.println("title : \n\n\n\n"+title.first().text());
            //System.out.println("//===============================================");




            /*System.out.println("//===============================================");
            Whitelist wl = Whitelist.simpleText();
            wl.addTags("div", "span","del"); // add additional tags here as necessary
            String clean = Jsoup.clean(title.get(1).text(), Whitelist.basic());
            System.out.println("clean"+clean);*/



            System.out.println("//===============================================");



            String x = title.get(1).outerHtml();



            Document doc2 = Jsoup.parse(x);
            //Elements del = doc2.select("del");
            doc2.select("del").remove();
            //System.out.println(doc2.select("del").text());
            String one = doc2.text();
            String two = doc2.select("del").text();
            String replaceAll = one.replaceAll(Pattern.quote(" "+two+" ").replaceAll(Pattern.quote(" "),""), "");
            String trim = replaceAll.replaceAll(Pattern.quote(" "), "");
            System.out.println("replaceAll" +trim );

            //System.out.println("one" + doc2.text());
            //System.out.println("two" + two);

            System.out.println("//===============================================");



            old.add(replaceAll.trim());
            old.add(title.first().text());
            old.add(links4.first().text());











        } catch (IOException e) {
            e.printStackTrace();
        }
        return old;
    }


    @Override
    protected void onPostExecute(List<String> items) {



        /*mAdapter = new Adapter(items_mada,items_mada_num,items_update,items_old,mContext,recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();*/
    }
}
