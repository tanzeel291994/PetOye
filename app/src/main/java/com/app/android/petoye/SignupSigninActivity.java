package com.app.android.petoye;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupSigninActivity extends AppCompatActivity {
    TextView heading;
    public static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_signin);
        heading = (TextView)findViewById(R.id.heading);
        context=getApplicationContext();
        Typeface custom_font = Typeface.createFromAsset(getAssets(),"fonts/NoteworthyLight.ttf");
        heading.setTypeface(custom_font);
        Bundle bundle = getIntent().getExtras();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        if(Objects.equals(bundle.getString("fragment_name"), "fragment_create_account"))
        {
            CreateAccount createAccountFragment=new CreateAccount();
            fragmentTransaction.add(R.id.container_signup_and_signin,createAccountFragment);
        }
        else if(Objects.equals(bundle.getString("fragment_name"), "fragment_login"))
        {
            LoginAccount loginAccountFragment=new LoginAccount();
            fragmentTransaction.add(R.id.container_signup_and_signin,loginAccountFragment);
        }
        else
        {
            Log.i("TAG","throw an error");
        }

        fragmentTransaction.commit();

    }
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
