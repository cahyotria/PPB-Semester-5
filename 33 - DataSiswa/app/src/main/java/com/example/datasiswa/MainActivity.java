package com.example.datasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab_add;
    private ListView listView;

    private ListViewAdapter adapter;

    private ArrayList<dataSiswa> listData = new ArrayList<>();
    private DataSiswaOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab_add     = (FloatingActionButton) findViewById(R.id.fab_add);
        listView    = (ListView) findViewById(R.id.listview);

        helper      = new DataSiswaOpenHelper(this);

        viewData();

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveInput = new Intent(MainActivity.this , InputActivity.class);
                startActivity(moveInput);
            }
        });
    }

    private void viewData(){
        listData.clear();
        Cursor res = helper.getData();
        while(res.moveToNext()){
            String id   = res.getString(0);
            String nim  = res.getString(1);
            String nama = res.getString(2);

            listData.add(new dataSiswa(id,nim, nama));
        }
        adapter = new ListViewAdapter(listData, MainActivity.this);
        listView.setAdapter(adapter);
    }

}