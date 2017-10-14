package com.example.vduder.vduder.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StyleableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.vduder.vduder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, UserListActivity.class);
        startActivity(intent);
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
//                            Toast.makeText(MainActivity.this, "МЫ ЗАШЛИ СУКААА",
//                                    Toast.LENGTH_SHORT).show();
//                            writeNewUser("Dmitry", "dima.yonkov@gmail.com");
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w("dd", "signInWithEmail:failure", task.getException());
//                            Toast.makeText(MainActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                        // ...
//                    }
//                });
        }
//
//    private void writeNewUser(String name, String email) {
//        User user = new User(name, email);
//
//        mDatabase.child("users").child().setValue(user);
//    }

}
