package com.example.vduder.vduder.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StyleableRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.vduder.vduder.Model.Role;
import com.example.vduder.vduder.R;
import com.example.vduder.vduder.fragment.LoginFragment;
import com.example.vduder.vduder.fragment.RegistrationFragment;
import com.example.vduder.vduder.fragment.RoleFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private LoginFragment loginFrag;
    private RoleFragment roleFrag;
    private FragmentTransaction fTrans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, SoundActivity.class);
        intent.putExtra("isAdv", false);
        startActivityForResult(intent, 1234);

//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user == null) {
//            loginFrag = new LoginFragment();
//            fTrans = getSupportFragmentManager().beginTransaction();
//            fTrans.add(R.id.frgmCont, loginFrag);
//            fTrans.commit();
//        } else {
//            roleFrag = new RoleFragment();
//            fTrans = getSupportFragmentManager().beginTransaction();
//            fTrans.add(R.id.frgmCont, roleFrag);
//            fTrans.commit();
//        }

//        Intent intent = new Intent(this, UserListActivity.class);
//        intent.putExtra(Role.RoleIntentKey, Role.GuestRole);
//        startActivity(intent);
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            loginFrag = new LoginFragment();
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.add(R.id.frgmCont, loginFrag);
            fTrans.commit();
        } else {
            roleFrag = new RoleFragment();
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.add(R.id.frgmCont, roleFrag);
            fTrans.commit();
        }
    }

}
