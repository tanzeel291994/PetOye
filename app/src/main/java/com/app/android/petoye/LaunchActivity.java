package com.app.android.petoye;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

public class LaunchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
    }
    public void gotoCreateAccountScreen(View view)
    {
        Intent i = new Intent(this, SignupSigninActivity.class);
        i.putExtra("fragment_name","fragment_create_account");
        startActivity(i);
    }

    ///------------if create account pressed -------------///////
    public void gotoLoginScreen(View view)
    {
        Intent i = new Intent(this, SignupSigninActivity.class);
        i.putExtra("fragment_name","fragment_login");
        startActivity(i);
    }
}
