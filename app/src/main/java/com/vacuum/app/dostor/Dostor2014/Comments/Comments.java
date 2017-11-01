package com.vacuum.app.dostor.Dostor2014.Comments;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.vacuum.app.dostor.R;
import com.vacuum.app.dostor.Tasks.Comments_task;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Home on 10/2/2017.
 */

public class Comments extends Activity {

    static Context context;
    static ListView listView;
    static String comments_link;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
        Bundle bundle = getIntent().getExtras();

        comments_link = bundle.getString("comment_link");

        if (savedInstanceState == null) {
            F1.newInstance().show(getFragmentManager(), null);
        }
    }

    public static class F1 extends DialogFragment {
        public Vibrator vibe;

        public static F1 newInstance() {
            F1 f1 = new F1();


            f1.setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
            return f1;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.popup_layout, container, false);
            // Remove the default background
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            vibe = (Vibrator)v.getContext().getSystemService(Context.VIBRATOR_SERVICE);

            // Inflate the new view with margins and background

            // Set up a click listener to dismiss the popup if they click outside
            // of the background view

            RecyclerView recyclerView =  v.findViewById(R.id.commentsListView);
            Button post =  v.findViewById(R.id.post);
            post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vibe.vibrate(100);
                    Toast.makeText(context, "Go Preimum first", Toast.LENGTH_SHORT).show();
                }
            });

            v.findViewById(R.id.popup_root).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    ((Activity)context).finish();

                }
            });
            setSimpleList(recyclerView);
            return v;
        }
        void setSimpleList(RecyclerView recyclerView){


            new Comments_task(context,recyclerView).execute(comments_link);

        }

        @Override
        public void onDetach() {
            super.onDetach();
            ((Activity)context).finish();
        }
    }
}