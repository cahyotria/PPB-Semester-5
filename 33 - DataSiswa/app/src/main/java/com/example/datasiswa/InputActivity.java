package com.example.datasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InputActivity extends AppCompatActivity {

    private FloatingActionButton fab_done, fab_cancel;
    private EditText et_NIM, et_Nama;

    private DataSiswaOpenHelper helper;
    private String pilih = "Tambah";
    private String id, nim, nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab_done    = (FloatingActionButton) findViewById(R.id.fab_done);
        fab_cancel  = (FloatingActionButton) findViewById(R.id.fab_cancel);

        et_NIM      = (EditText) findViewById(R.id.et_nim);
        et_Nama     = (EditText) findViewById(R.id.et_nama);

        helper      = new DataSiswaOpenHelper(this);

        boolean cekid = getIntent().hasExtra("ID");
        boolean ceknim = getIntent().hasExtra("NIM");
        boolean ceknama = getIntent().hasExtra("NAMA");
        boolean cekpilih = getIntent().hasExtra("TANDA");

        if(cekid && ceknim && ceknama && cekpilih){
            getSupportActionBar().setTitle("Ubah Data");
            id      = getIntent().getStringExtra("ID");
            nim     = getIntent().getStringExtra("NIM");
            nama    = getIntent().getStringExtra("NAMA");
            pilih   = getIntent().getStringExtra("TANDA");

            et_NIM.setText(nim);
            et_Nama.setText(nama);
        }else{
            getSupportActionBar().setTitle("Tambah Data");
        }

        fab_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nim     = et_NIM.getText().toString();
                nama    = et_Nama.getText().toString();

                if(TextUtils.isEmpty(nim) && TextUtils.isEmpty(nama)){
                    Toast.makeText(InputActivity.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }else{
                    if(pilih.equals("Tambah")){
                        boolean isInsert = helper.insertData(nim, nama);
                        if(isInsert){
                            Toast.makeText(InputActivity.this, "Data Telah Berhasil di Simpan", Toast.LENGTH_SHORT).show();
                            empty();
                            Intent moveMain = new Intent(InputActivity.this, MainActivity.class);
                            startActivity(moveMain);
                        }else{
                            Toast.makeText(InputActivity.this, "Data Gagal di Simpan", Toast.LENGTH_SHORT).show();
                        }
                    }else if(pilih.equals("Ubah")){
                        boolean isUpdate = helper.updateData(id,nim,nama);
                        if(isUpdate){
                            Toast.makeText(InputActivity.this, "Data Telah Update", Toast.LENGTH_SHORT).show();
                            empty();
                            Intent moveMain = new Intent(InputActivity.this, MainActivity.class);
                            startActivity(moveMain);
                        }else{
                            Toast.makeText(InputActivity.this, "Data Gagal di Update", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        fab_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empty();
                Toast.makeText(InputActivity.this, "Reset", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void empty(){
        et_NIM.setText("");
        et_Nama.setText("");
    }
}