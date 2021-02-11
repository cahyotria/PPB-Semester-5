package com.example.loginregistrasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Registrasi extends AppCompatActivity {

    EditText mNamaLengkapEdit, mUsernameEdit, mPasswordedit;
    Button mRegistrasiButton, mResetButton;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public static final String session = "session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        mNamaLengkapEdit        = (EditText) findViewById(R.id.editNamaLengkapRegis);
        mUsernameEdit           = (EditText) findViewById(R.id.editUsernameRegis);
        mPasswordedit           = (EditText) findViewById(R.id.editPasswordRegis);

        mRegistrasiButton       = (Button) findViewById(R.id.btnRegistrasi);
        mResetButton            = (Button) findViewById(R.id.btnReset);

        mRegistrasiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref        = getSharedPreferences(session, MODE_PRIVATE);
                editor      = pref.edit();
                editor.putString("namaLengkap", mNamaLengkapEdit.getText().toString());
                editor.putString("username", mUsernameEdit.getText().toString());
                editor.putString("password", mPasswordedit.getText().toString());
                editor.apply();

                Toast.makeText(Registrasi.this, "Registrasi Sukses", Toast.LENGTH_LONG).show();
            }
        });

        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNamaLengkapEdit.setText("");
                mUsernameEdit.setText("");
                mPasswordedit.setText("");
            }
        });

    }
}