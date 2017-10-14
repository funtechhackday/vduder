package com.example.vduder.vduder.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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

import com.example.vduder.vduder.Model.User;
import com.example.vduder.vduder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by dmitry on 14.10.17.
 */

public class RegistrationFragment extends Fragment {

    private static final int LAYOUT = R.layout.fragment_registration;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;

    private EditText nameEdit;
    private EditText emailEdit;
    private EditText passEdit;
    private EditText moneyEdit;
    private Button regBtn;
    private Button cancelBtn;
    private LoginFragment logFrag;
    private FragmentManager fragmentManager;

    private String name;
    private String email;
    private String money;
    private String pass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);
        nameEdit = (EditText) view.findViewById(R.id.input_name_reg);
        emailEdit = (EditText) view.findViewById(R.id.input_email_reg);
        passEdit = (EditText) view.findViewById(R.id.input_password_reg);
        moneyEdit = (EditText) view.findViewById(R.id.input_money_reg);
        regBtn = (Button) view.findViewById(R.id.btn_reg);
        cancelBtn = (Button) view.findViewById(R.id.btn_cancel);


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentRouting();
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    void createUser( ) {
        name = nameEdit.getText().toString();
        email = emailEdit.getText().toString();
        pass = passEdit.getText().toString();
        money = moneyEdit.getText().toString();

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getActivity(), "ТЫ ЗАРЕГАЛСЯ",
                                    Toast.LENGTH_SHORT).show();
                            writeNewUser(user.getUid(), name, email, money);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("dd", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            fragmentRouting();
                        }

                    }
                });
    }

    private void writeNewUser(String id, String username, String email, String money) {
        User user = new User(id, username, email, money);
        mDatabase.child("users").child(id).setValue(user);
        fragmentRouting();
    }
    private void fragmentRouting() {
        logFrag = new LoginFragment();
        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frgmCont, logFrag);
        fragmentTransaction.commit();
    }

}