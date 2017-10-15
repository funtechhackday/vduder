package com.example.vduder.vduder.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vduder.vduder.Core.IdGenerator;
import com.example.vduder.vduder.Core.UserListInfo;
import com.example.vduder.vduder.Core.UserListViewAdapter;
import com.example.vduder.vduder.Model.Interview;
import com.example.vduder.vduder.Model.Order;
import com.example.vduder.vduder.Model.Role;
import com.example.vduder.vduder.Model.User;
import com.example.vduder.vduder.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class UserListActivity extends AppCompatActivity
{
    private DatabaseReference dataBase;

    private ListView userListView;
    UserListViewAdapter adapter;
    private Button showAllInterviewsButton;
    private String myRole;
    private String myId;

    private ArrayList<User> allUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        myRole = getIntent().getStringExtra(Role.RoleIntentKey);
        myId = GetMyId();

        userListView = (ListView) findViewById(R.id.userListView);
        showAllInterviewsButton = (Button) findViewById(R.id.showInterviewListButton);
        showAllInterviewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToAllIterviewsActivity();
            }
        });

        allUsers = new ArrayList<>();
        dataBase = FirebaseDatabase.getInstance().getReference();
        ReloadView();

        InitUserLoading();
        InitCurrentOrdersUpdate();

        Order.RemoveFromDataBase("1", "2");
    }

    private void GoToAllIterviewsActivity() {
        Intent intent = new Intent(this, InterviewListActivity.class);
        startActivity(intent);
    }

    private Boolean IsFirstTime = true;

    private void InitCurrentOrdersUpdate() {
        if (!IsFirstTime)
        {
            dataBase.child("Orders").removeEventListener(orderListener);
        }
        ReloadListener();
        dataBase
                .child("Orders")
                .addValueEventListener(orderListener);
        IsFirstTime = false;
    }

    private void ReloadListener() {
        orderListener  = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    Order order = snapshot.getValue(Order.class);
                    if (!(IsOrderWithMe(order))) continue;

                    if (order.status.equals(Order.WaitStatus))
                    {
                        if (IsIAmAuthor(order)) //я - автор заявки
                        {
                            adapter.SetButtonAction(order.toUserId, "wait", false);
                        }
                        else //я - цель заявки
                        {
                            adapter.SetButtonAction(order.fromUserId, "go", true);
                        }
                    }
                    else if (order.status.equals(Order.ConfirmedStatus))
                    {
                        String opponentId;
                        if (IsIAmAuthor(order))
                            opponentId = order.toUserId;
                        else
                            opponentId = order.fromUserId;
                        adapter.SetButtonAction(opponentId, "go", true);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                ShowDataBaseError("error order load");
            }
        };
    }

    private Boolean IsIAmAuthor(Order order)
    {
        return order.fromUserId.equals(myId);
    }

    private ValueEventListener orderListener;

    private Boolean IsOrderWithMe(Order order)
    {
        return order.toUserId.equals(myId)
                || order.fromUserId.equals(myId);
    }

    public void OnListViewItemButtonClicked(int i, String userId, String status)
    {
        switch (status)
        {
            case "send":
                SendOrder(userId);
                break;
            case "go":
                GoToInterview(userId);
                break;
        }
    }

    private void GoToInterview(final String opponentId) {
        String opponentField = myRole.equals(Role.VdudRole) ? "guestUserId" : "vdudUserId";
        dataBase
                .child("interview")
                .orderByChild(opponentField)
                .equalTo(opponentId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            Interview interview = snapshot.getValue(Interview.class);
                            GoToInterviewActivity(interview.id);
                            return;
                        }
                        GoToNewInterview(opponentId);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        ShowDataBaseError("error interview check");
                    }
                });
    }

    private void GoToNewInterview(String opponentId) {
        Interview interview = CreateNewInterviewInBase(opponentId);
        SetOrderToConfirmed(opponentId, myId);

        GoToInterviewActivity(interview.id);
    }

    @NonNull
    private Interview CreateNewInterviewInBase(String opponentId) {
        String id = IdGenerator.GenerateId();
        String vdudUserId;
        String guestUserId;
        if (Objects.equals(myRole, Role.VdudRole))
        {
            vdudUserId = myId;
            guestUserId = opponentId;
        }
        else
        {
            vdudUserId = opponentId;
            guestUserId = myId;
        }

        Interview interview = new Interview(id, vdudUserId, guestUserId);
        dataBase.child("interview").child(interview.id).setValue(interview);
        return interview;
    }

    private void SetOrderToConfirmed(final String fromId, final String toId) {
        dataBase
                .child("Orders")
                .orderByChild("fromUserId")
                .equalTo(fromId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            Order order = snapshot.getValue(Order.class);
                            Boolean isInterviewOrder = order.toUserId.equals(toId);
                            if (isInterviewOrder)
                            {
                                order.status = Order.ConfirmedStatus;
                                dataBase.child("Orders").child(order.Id).setValue(order);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        ShowDataBaseError("error when set order to confirmed");
                    }
                });
    }

    private void GoToInterviewActivity(String interviewId)
    {
        Intent intent = new Intent(this, InterviewActivity.class);
        intent.putExtra("interviewId", interviewId);
        intent.putExtra(Role.RoleIntentKey, myRole);
        startActivity(intent); //TODO
//        Toast.makeText(this, "Go to interview with id " + interviewId, Toast.LENGTH_SHORT).show();
    }

    private void SendOrder(String userId)
	{
        String id = IdGenerator.GenerateId();
        Order order = new Order();
        order.Id = id;
        order.status = Order.WaitStatus;
        order.fromUserId = myId;
        order.toUserId = userId;

        dataBase.child("Orders").child(id).setValue(order);
    }

    private static String GetMyId()
    {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }


    private void InitUserLoading()
    {
        dataBase
                .child("role")
                .child(Role.GetOpponentRole(myRole))
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<Role> roles = new ArrayList<>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            Role role = snapshot.getValue(Role.class);
                            roles.add(role);
                        }
                        InitUsers(roles);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        ShowDataBaseError("error role load");
                    }
                });
    }

    private ArrayList<Role> dbRoles;

    private void InitUsers(ArrayList<Role> roles)
    {
        dbRoles = roles;
        dataBase
                .child("users")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        allUsers.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            User user = snapshot.getValue(User.class);
                            if (user.ID == null) continue;
                            if (Objects.equals(user.ID, myId)) continue;
                            for (Role role : dbRoles)
                            {
                                if (user.ID.equals(role.userID))
                                {
                                    allUsers.add(user);
                                    break;
                                }
                            }
                        }
                        ReloadView();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        ShowDataBaseError("error user load");
                    }
                });

    }

    private void ReloadView()
    {
        ArrayList<UserListInfo> infos = new ArrayList<>(allUsers.size());
        for (int i = 0; i < allUsers.size(); ++i)
        {
            UserListInfo info = new UserListInfo();
            User user = allUsers.get(i);
            info.userId = user.ID;
            info.userName = user.username;
            info.status = "send";
            infos.add(info);
        }
        adapter = new UserListViewAdapter(this, infos);
        userListView.setAdapter(adapter);

        InitCurrentOrdersUpdate();
    }

    private static void ShowDataBaseError(String message)
    {
        Log.e("Error", message);
    }

}