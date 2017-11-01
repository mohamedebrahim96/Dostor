package com.vacuum.app.dostor.NavigationDrawer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.audiofx.Equalizer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.facebook.login.LoginManager;
import com.kyleduo.switchbutton.SwitchButton;
import com.vacuum.app.dostor.MainActivity;
import com.vacuum.app.dostor.R;

import static android.content.Context.MODE_PRIVATE;
import static com.vacuum.app.dostor.SplashScreen.MY_PREFS_NAME;


/**
 * Created by Home on 10/19/2017.
 */

public class SettingsFragment extends Fragment implements CompoundButton.OnCheckedChangeListener,View.OnClickListener {

    Button clearcach;
    SharedPreferences.Editor editor;
    Context mContext;
    SwitchButton sb1,sb3,sb4,sb5,sb6,sb7,sb8;
    Vibrator vibe;
    RelativeLayout terms,policy,Themes,Language;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = this.getActivity();
        View view = inflater.inflate(R.layout.settings_fragment, container, false);
        mContext = this.getActivity();


        vibe = (Vibrator)mContext.getSystemService(Context.VIBRATOR_SERVICE);
        clearcach =  view.findViewById(R.id.clearcach);
        terms = view.findViewById(R.id.terms);
        policy = view.findViewById(R.id.policy);
        Themes = view.findViewById(R.id.Themes);
        Language = view.findViewById(R.id.Language);


        sb1=  view.findViewById(R.id.sb_default_measure1);
        sb3 =  view.findViewById(R.id.sb_default_measure3);
        sb4 =  view.findViewById(R.id.sb_default_measure4);
        sb5 =  view.findViewById(R.id.sb_default_measure5);
        sb6 =  view.findViewById(R.id.sb_default_measure6);
        sb7 =  view.findViewById(R.id.sb_default_measure7);
        sb8 =  view.findViewById(R.id.sb_default_measure8);






        clearcach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                editor = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.clear().commit();
                Toast.makeText(mContext,"Cache Cleared",Toast.LENGTH_SHORT).show();

            }
        });

        sb1.setOnCheckedChangeListener(this);
        sb3.setOnCheckedChangeListener(this);
        sb4.setOnCheckedChangeListener(this);
        sb5.setOnCheckedChangeListener(this);
        sb6.setOnCheckedChangeListener(this);
        sb7.setOnCheckedChangeListener(this);
        sb8.setOnCheckedChangeListener(this);

        terms.setOnClickListener(this);
        policy.setOnClickListener(this);
        Themes.setOnClickListener(this);
        Language.setOnClickListener(this);
        return view;
    }

    @Override
    public void onCheckedChanged(final CompoundButton compoundButton, boolean b) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(compoundButton.isChecked()){
                    compoundButton.setChecked(false);
                    Toast.makeText(mContext, "Go Preimum first", Toast.LENGTH_SHORT).show();
                    vibe.vibrate(100);
                }
            }
        }, 500);

    }

    @Override
    public void onClick(View view) {
        if(view == policy){
            startActivity(new Intent(mContext, PrivacyPolicyActivity.class));
            getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }else if(view==Themes||view==Language){
            Toast.makeText(mContext, "Go Preimum first", Toast.LENGTH_SHORT).show();
            vibe.vibrate(100);
        }else {
            startActivity(new Intent(mContext, TermsConditions.class));
            getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }

    }
}