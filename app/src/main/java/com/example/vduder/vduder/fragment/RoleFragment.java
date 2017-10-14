package com.example.vduder.vduder.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.vduder.vduder.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by dmitry on 14.10.17.
 */


public class RoleFragment extends Fragment {

    private static final int LAYOUT = R.layout.fragment_role;


    private LoginFragment loginFrag;
    private FragmentManager fragmentManager;

    private Button dudeBtn;
    private Button noDudeBtn;
    private Button logOutBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);
        dudeBtn = (Button) view.findViewById(R.id.btn_dude);
        noDudeBtn = (Button) view.findViewById(R.id.btn_noDude);
        logOutBtn = (Button) view.findViewById(R.id.btn_logOut);

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });
        return view;
    }

    void logOut() {
        FirebaseAuth.getInstance().signOut();
        loginFrag = new LoginFragment();
        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frgmCont, loginFrag);
        fragmentTransaction.commit();
    }
}