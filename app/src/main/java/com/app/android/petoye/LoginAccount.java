package com.app.android.petoye;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.facebook.FacebookSdk;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginAccount extends Fragment {
    Pattern pattern;
    Matcher matcher;
    TextInputLayout usernameWrapper;
    TextInputLayout passwordWrapper;

    public LoginAccount() {
        // Required empty public constructor
    }


    public static LoginAccount newInstance(String param1, String param2) {
        LoginAccount fragment = new LoginAccount();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  FacebookSdk.sdkInitialize(SignupSigninActivity.context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login_account, container, false);
        usernameWrapper = (TextInputLayout) view.findViewById(R.id.usernameWrapper);
        passwordWrapper = (TextInputLayout) view.findViewById(R.id.passwordWrapper);
        final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        usernameWrapper.setHint("Username");
        passwordWrapper.setHint("Password");


        Button button = (Button) view.findViewById(R.id.login_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("tag","in");
                String username = usernameWrapper.getEditText().getText().toString();
                String password = passwordWrapper.getEditText().getText().toString();
                // boolean email_check=validateEmail(username);
                // boolean password_check=validatePassword(password);
                if (!validateEmail(username)) {
                    usernameWrapper.setError("Not a valid email address!");
                } else if (!validatePassword(password)) {
                    passwordWrapper.setError("Not a valid password!");
                } else {
                    usernameWrapper.setErrorEnabled(false);
                    passwordWrapper.setErrorEnabled(false);
                    //doLogin();
                }

            }
        });
        return view;
    }

    public boolean validateEmail(String email) {

        matcher = pattern.matcher(email);
        return matcher.matches();

    }

    public boolean validatePassword(String password) {
        return password.length() > 8;
    }
}
