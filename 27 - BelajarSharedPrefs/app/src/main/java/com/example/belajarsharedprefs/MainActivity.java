package com.example.belajarsharedprefs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mNamaLengkapEdit, mAlamatEdit;
    Button mSaveButton, mCancelButton, mLoadButton;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public static final String session = "session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNamaLengkapEdit    = (EditText) findViewById(R.id.editNamaLengkap);
        mAlamatEdit         = (EditText) findViewById(R.id.editAlamat);

        mSaveButton         = (Button) findViewById(R.id.btnSave);
        mCancelButton       = (Button) findViewById(R.id.btnCancel);
        mLoadButton         = (Button) findViewById(R.id.btnLoad);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences     = getSharedPreferences(session,MODE_PRIVATE);
                editor          = preferences.edit();
                editor.putString("getNamaLengkap", mNamaLengkapEdit.getText().toString());
                editor.putString("getAlamat", mAlamatEdit.getText().toString());
                editor.apply();

                Toast.makeText(MainActivity.this, "Data Telah Tersimpan", Toast.LENGTH_SHORT).show();

                //RESET
                mNamaLengkapEdit.setText("");
                mAlamatEdit.setText("");
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = preferences.edit();
                editor.remove("getNamaLengkap");
                editor.remove("getAlamat");
                editor.apply();

                mNamaLengkapEdit.setText(" ");
                mAlamatEdit.setText(" ");

                Toast.makeText(MainActivity.this, "Data Telah Terhapus", Toast.LENGTH_SHORT).show();

            }
        });

        mLoadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences         = getSharedPreferences(session,MODE_PRIVATE);
                mNamaLengkapEdit.setText(preferences.getString("getNamaLengkap", " "));
                mAlamatEdit.setText(preferences.getString("getAlamat", " "));
                Toast.makeText(MainActivity.this, "Data Telah Terload", Toast.LENGTH_SHORT).show();
            }
        });

    }



}