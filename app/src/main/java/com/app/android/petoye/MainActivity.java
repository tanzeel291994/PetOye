package com.app.android.petoye;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.graphics.Typeface;
import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Set a toolbar to replace the action bar.
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView txt = (TextView)findViewById(R.id.toolbar_title);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),"fonts/NoteworthyLight.ttf");
        txt.setTypeface(custom_font);
        //toolbar.setNavigationIcon(R.drawable.petoye_logo);

        setSupportActionBar(toolbar);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Home home = new Home();
        fragmentTransaction.add(R.id.main_fragment_container,home,"home");
        fragmentTransaction.commit();

    }

}