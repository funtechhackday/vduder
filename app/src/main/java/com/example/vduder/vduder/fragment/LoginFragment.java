package com.example.vduder.vduder.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vduder.vduder.R;
import com.google.android.gms.common.api.BatchResultToken;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by dmitry on 14.10.17.
 */

public class LoginFragment extends Fragment {

    private static final int LAYOUT = R.layout.fragment_login;
    private TextView logUpTextView;
    private EditText emailEdit;
    private EditText passEdit;
    private Button logInBtn;

    private FragmentActivity myContext;

    private RegistrationFragment regFrag;
    private RoleFragment roleFrag;
    private FragmentManager fragmentManager;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private String email;
    private String pass;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);
        logUpTextView = (TextView) view.findViewById(R.id.link_signup);
        emailEdit = (EditText) view.findViewById(R.id.input_email);
        passEdit = (EditText) view.findViewById(R.id.input_password);
        logInBtn = (Button) view.findViewById(R.id.btn_logIn);
        regFrag = new RegistrationFragment();
        fragmentManager = getFragmentManager();
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singIn();
            }
        });

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

    void singIn() {
        email = emailEdit.getText().toString();
        pass = passEdit.getText().toString();
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getActivity(), "Вы залогинились",
                                    Toast.LENGTH_SHORT).show();
                            fragmentRouting();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("dd", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void fragmentRouting() {
        roleFrag = new RoleFragment();
        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frgmCont, roleFrag);
        fragmentTransaction.commit();
    }

}