package com.vacuum.app.dostor.Lagna_50_asasy;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vacuum.app.dostor.Lagna_50_e7taty.Adapter_Lagna_e7taty;
import com.vacuum.app.dostor.Model.Item;
import com.vacuum.app.dostor.Model.Person;
import com.vacuum.app.dostor.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by Home on 2017-09-09.
 */

public class Fragment_Lagna_asasy extends Fragment {

    RecyclerView recyclerView;
    View view;
    public static int x = 0;
    public static int m = 0;
    ProgressDialog dialog;
    Context mContext;
    Adapter_Lagna_e7taty lagna_asasy;
    List<String> name = new ArrayList<>();
    List<String> imageLink  = new ArrayList<>();
    List<String> details_array  = new ArrayList<>();
    List<String> dataset = new ArrayList<>();

    List<Item> items  ;
    Realm realm;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.fragment_two, container, false);
            mContext = getActivity();
            recyclerView =  view.findViewById(R.id.recycler_view_two);
        dialog = new ProgressDialog(mContext);
        dialog.setMessage("Please wait five seconds...");
        dialog.setTitle("Waiting...");
        dialog.setCancelable(false);

        x=0;
        m=0;

        realm = Realm.getDefaultInstance();

        if(realm.where(Person.class).equalTo("tableAndId","asasy").findAll().size()!=0)
        {
            List<Person> results = realm.where(Person.class).equalTo("tableAndId","asasy").findAll();
            make_adapter2(results);
            //Toast.makeText(mContext, "Realm Size is :"+results.size(), Toast.LENGTH_SHORT).show();
        }else {
            //clearRealm();
            for (int i = 0; i <= 5; i++) {
                new LagnaTask().execute("http://c50.dostour.eg/?cat=2&paged=" + i);
            }
        }

        return view;
    }

    private void make_adapter2(List<Person> persons) {
        lagna_asasy = new Adapter_Lagna_e7taty(persons,mContext);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(lagna_asasy);
    }
    private void make_adapter() {
        items = new ArrayList<>();
        for (int i=0;i<name.size();i++)
        {
            items.add(new Item(name.get(i),dataset.get(i),imageLink.get(i)));
        }
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                for (Item x:items)
                {
                    Person book = realm.createObject(Person.class);
                    book.setName(x.getMada_title());
                    book.setJob(x.getMada_num());
                    book.setImageLink(x.getImageLink());
                    book.setTableAndId("asasy");
                }
            }
        });
        make_adapter2(realm.where(Person.class).equalTo("tableAndId","asasy").findAll());
    }
    public class LagnaTask extends AsyncTask<String, Object, List<String>> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            dialog.show();
        }
        @Override
        protected List<String> doInBackground(String... params) {
            Document doc;
            try {
                doc = Jsoup.connect(params[0]).get();

                Elements links = doc.select("li.span3");
                Elements links2 = links.select("div.thumbnail");
                Elements src = links2.select("img[src]");
                Elements details = links2.select("a[href]");

                    //System.out.println("=============================");
                    //System.out.println("=============================");
                    for (Element linksrc : src) {

                        imageLink.add(linksrc.attr("src"));
                    }
                    for (Element link_title : links2) {
                        name.add(link_title.text());
                    }
                for (Element link_details : details) {
                    details_array.add(link_details.attr("href"));
                    //System.out.println(link_details.attr("href"));
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(List<String> items2) {

            ++x;
            if (x == 6)
            {

                new LagnaTask_details2().execute();
            }
            }
        }


    public class LagnaTask_details2 extends AsyncTask<String, Object, List<String>> {

        String details_list ;



        @Override
        protected void onPreExecute(){
            super.onPreExecute();


        }

        @Override
        protected List<String> doInBackground(String... voids) {

            for(String link:details_array)
            {
                m++;
                Document doc;
                try {
                    doc = Jsoup.connect(link).get();

                    Elements links = doc.select("div.post_content");

                    System.out.println("=============================");
                    details_list = links.text();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(details_list.trim().length() == 0){
                    dataset.add( "يبحث عن وظيفة");
                    System.out.println("null "+details_list);
                }else {
                    dataset.add(details_list);
                    System.out.println("A7a: "+details_list);
                }
            }

            return null;
        }


        @Override
        protected void onPostExecute(List<String> details) {
            if(m==details_array.size())
            {
                System.out.println(":::::::::::::::::::::::::::::::::");
                System.out.println("details_array.size()"+details_array.size());
                make_adapter();
                dialog.dismiss();
            }
        }
    }

    public void clearRealm()
    {
        try {
            realm.close();
            Realm.deleteRealm(realm.getConfiguration());
            //Realm file has been deleted.
        } catch (Exception ex){
            ex.printStackTrace();
            //No Realm file to remove.
        }
    }
}
