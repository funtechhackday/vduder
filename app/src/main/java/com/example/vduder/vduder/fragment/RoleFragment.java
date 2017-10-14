package com.example.vduder.vduder.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vduder.vduder.R;

/**
 * Created by dmitry on 14.10.17.
 */


public class RoleFragment extends Fragment {

    private static final int LAYOUT = R.layout.fragment_role;


    private FragmentActivity myContext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);

        return view;
    }

    void singIn() {

    }
}