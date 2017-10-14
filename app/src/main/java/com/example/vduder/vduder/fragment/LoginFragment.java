package com.example.vduder.vduder.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vduder.vduder.R;

/**
 * Created by dmitry on 14.10.17.
 */

public class LoginFragment extends Fragment {

    private static final int LAYOUT = R.layout.fragment_login;
    private TextView logUpTextView;
    private EditText emailEdit;
    private EditText passEdit;

    private FragmentActivity myContext;

    private RegistrationFragment regFrag;
    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);
        logUpTextView = (TextView) view.findViewById(R.id.link_signup);
        emailEdit = (EditText) view.findViewById(R.id.input_email);
        passEdit = (EditText) view.findViewById(R.id.input_password);
        regFrag = new RegistrationFragment();
        fragmentManager = getFragmentManager();


        logUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frgmCont, regFrag);
                fragmentTransaction.commit();
            }

        });
        return view;
    }


}