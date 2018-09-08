package com.example.efnangul.firebasedatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.TooManyListenersException;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final int REQUEST_LOGIN_MODE = 28;
    public static final String EXTRA_LOGIN_EMAIL = "login_email";

    public enum LOGIN_MODE {admin, other}

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton mAddUserBtn;

    ArrayList<UserModel> myDataset = new ArrayList<>();
    ArrayList<UserModel> myAdapterDataset = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_card_list);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyRecyclerViewAdapter(myAdapterDataset, new MyRecyclerViewAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                //TODO: Get card view data
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        mAddUserBtn = (FloatingActionButton) findViewById(R.id.fab_add_user);
        mAddUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Add user with admin
                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Users");
                dbRef.push().setValue(
                        new UserModel(
                                "ieg@gmail.com",
                                "ieg",
                                "ieg",
                                123,
                                "home",
                                12,
                                UserModel.Sex.MALE
                        )
                );
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myDataset.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserModel user = snapshot.getValue(UserModel.class);
                    if (user != null) {
                        myDataset.add(
                                new UserModel(
                                        user.getEmail(),
                                        user.getName(),
                                        user.getLastName(),
                                        user.getSalary(),
                                        user.getDepartment(),
                                        user.getAge(),
                                        user.getSex()
                                )
                        );
                        Log.e(TAG, "onDataChange: " + user.getEmail());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, databaseError.getDetails());
            }

        });

        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, REQUEST_LOGIN_MODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_LOGIN_MODE)
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    LOGIN_MODE mode = (LOGIN_MODE) data.getSerializableExtra("LOGIN_MODE");
                    String email = data.getStringExtra(EXTRA_LOGIN_EMAIL);
                    switch (mode) {
                        case admin: {
                            setAllUsers();
                            break;
                        }
                        case other: {
                            setOneUser(email);
                            break;
                        }
                    }
                }
            }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setOneUser(String email) {
        if (myAdapterDataset == null)
            return;

        myAdapterDataset.clear();
        for (int i = 0; i < myDataset.size(); i++)
            if (myDataset.get(i).getEmail().equals(email))
                myAdapterDataset.add(myDataset.get(i));

        mAdapter.notifyDataSetChanged();
    }

    private void setAllUsers() {
        if (myAdapterDataset == null)
            return;

        myAdapterDataset.clear();
        myAdapterDataset = myDataset;
        mAdapter.notifyDataSetChanged();
    }
}
