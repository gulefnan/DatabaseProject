package com.example.efnangul.firebasedatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.TooManyListenersException;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton mAddUserBtn;

    ArrayList<UserModel> myDataset = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_card_list);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyRecyclerViewAdapter(getDataSet(), new MyRecyclerViewAdapter.MyClickListener() {
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
            }
        });

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private ArrayList<UserModel> getDataSet() {
        myDataset = new ArrayList<>();

        //TODO: Get card views

        myDataset.add(new UserModel("ieg@gmail.com", "ieg", "ieg", 123, "home", 12, UserModel.Sex.MALE));
        myDataset.add(new UserModel("ieg@gmail.com", "ieg1", "ieg", 123, "home", 12, UserModel.Sex.MALE));
        myDataset.add(new UserModel("ieg@gmail.com", "ieg2", "ieg", 123, "home", 12, UserModel.Sex.MALE));
        myDataset.add(new UserModel("ieg@gmail.com", "ieg3", "ieg", 123, "home", 12, UserModel.Sex.MALE));
        myDataset.add(new UserModel("ieg@gmail.com", "ieg4", "ieg", 123, "home", 12, UserModel.Sex.MALE));
        myDataset.add(new UserModel("ieg@gmail.com", "ieg5", "ieg", 123, "home", 12, UserModel.Sex.MALE));

        return myDataset;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
