package com.example.zantech;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrasiActivity extends Activity {

    EditText mNamaEdit, mUsernameEdit, mPasswordEdit;
    Button mRegisButton, mResetButton;

    private static final String SESSION = "session";

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        mNamaEdit       = (EditText) findViewById(R.id.editNamaLengkapRegis);
        mUsernameEdit   = (EditText) findViewById(R.id.editUsernameRegis);
        mPasswordEdit   = (EditText) findViewById(R.id.editPasswordRegis);

        mRegisButton    = (Button) findViewById(R.id.btnRegistasi);
        mResetButton    = (Button) findViewById(R.id.btnReset);

        mRegisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref = getSharedPreferences(SESSION, MODE_PRIVATE);
                editor  = pref.edit();
                editor.putString("namaLengkap", mNamaEdit.getText().toString());
                editor.putString("username", mUsernameEdit.getText().toString());
                editor.putString("password", mPasswordEdit.getText().toString());
                editor.apply();

                Toast.makeText(RegistrasiActivity.this, "Registrasi Berhasil", Toast.LENGTH_LONG).show();

                mPasswordEdit.setText("");
            }
        });

        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNamaEdit.setText("");
                mUsernameEdit.setText("");
                mPasswordEdit.setText("");
                Intent moveLogin = new Intent(RegistrasiActivity.this, MainActivity.class);
                startActivity(moveLogin);
            }
        });


    }
}