package com.app.android.petoye;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.graphics.Typeface;
import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static DisplayMetrics displayMetrics;
     ImageButton home_btn,discover_btn,myProfile_btn,notification_btn,plus_btn;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    TextView txt_action_bar;
   ImageButton whichButtonChecked;
    Home home;
    ProfileFragment profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home_btn=(ImageButton)findViewById(R.id.home_btn) ;
        discover_btn=(ImageButton)findViewById(R.id.discover_btn) ;
        myProfile_btn=(ImageButton)findViewById(R.id.profile_btn) ;
        notification_btn=(ImageButton)findViewById(R.id.notification_btn) ;
        plus_btn=(ImageButton)findViewById(R.id.btn_plus) ;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
         txt_action_bar = (TextView)findViewById(R.id.toolbar_title);
        displayMetrics = getResources().getDisplayMetrics();
        Typeface custom_font = Typeface.createFromAsset(getAssets(),"fonts/NoteworthyLight.ttf");
        txt_action_bar.setTypeface(custom_font);
       // toolbar.setNavigationIcon(R.drawable.m);
        whichButtonChecked=home_btn;
        home_btn.setColorFilter(Color.parseColor("#59c3cc"));

        setSupportActionBar(toolbar);
         fragmentManager = getSupportFragmentManager();
        home = new Home();
         profile = new ProfileFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_fragment_container,home,"home");
        fragmentTransaction.commit();


    }
    public void onClickDiscover(View view)
    {
        if(whichButtonChecked!=discover_btn)
            whichButtonChecked.clearColorFilter();
        discover_btn.setColorFilter(Color.parseColor("#59c3cc"));
        whichButtonChecked=discover_btn;
    }
    public void onClickHome(View view)
    {
        if(whichButtonChecked!=home_btn)
            whichButtonChecked.clearColorFilter();
        home_btn.setColorFilter(Color.parseColor("#59c3cc"));
        whichButtonChecked=home_btn;

        txt_action_bar.setText("PetOye");

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.main_fragment_container,home,"home");
       // fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public void onClickNotification(View view)
    {
        if(whichButtonChecked!=notification_btn)
            whichButtonChecked.clearColorFilter();
        notification_btn.setColorFilter(Color.parseColor("#59c3cc"));
        whichButtonChecked=notification_btn;
    }
    public void onClickMyProfile(View view)
    {
        if(whichButtonChecked != myProfile_btn)
            whichButtonChecked.clearColorFilter();
        myProfile_btn.setColorFilter(Color.parseColor("#59c3cc"));
        whichButtonChecked=myProfile_btn;

        txt_action_bar.setText("Profile");

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.main_fragment_container,profile,"profile");
        //fragmentTransaction.addToBackStack(null);
       fragmentTransaction.commit();
    }
    public void onClickPlusButton(View view)
    {

    }

}
