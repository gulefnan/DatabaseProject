package com.example.efnangul.firebasedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//Sadece adminin erişebileceği tüm çalışanların listesi

public class WorkerList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_list);
    }
}
