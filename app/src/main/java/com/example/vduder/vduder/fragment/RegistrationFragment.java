package com.example.vduder.vduder.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registration, null);
    }

//    void createUser() {
//        mAuth = FirebaseAuth.getInstance();
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        mAuth.signInWithEmailAndPassword("dima.yonkov@gmail.com", "11111111")
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d("dd", "signInWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            Toast.makeText(getActivity(), "МЫ ЗАШЛИ СУКААА",
//                                    Toast.LENGTH_SHORT).show();
//                            writeNewUser("Dmitry", "dima.yonkov@gmail.com");
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w("dd", "signInWithEmail:failure", task.getException());
//                            Toast.makeText(getActivity(), "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                        // ...
//                    }
//                });
 //   }
        private void writeNewUser(String name, String email) {
       // User user = new User(name, email);
      //  mDatabase.child("users").child(1).setValue(user);
    }

}