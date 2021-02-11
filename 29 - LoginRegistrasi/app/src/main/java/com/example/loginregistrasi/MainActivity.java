package com.example.loginregistrasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mUsernameEdit, mPasswordEdit;
    Button  mLoginButton, mRegistrasiButton;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public static final String session = "session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsernameEdit       = (EditText) findViewById(R.id.editUsername);
        mPasswordEdit       = (EditText) findViewById(R.id.editPassword);

        mLoginButton        = (Button) findViewById(R.id.btnLogin);
        mRegistrasiButton   = (Button) findViewById(R.id.btnRegistrasi);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mUsernameEdit.getText().toString();
                String pass = mPasswordEdit.getText().toString();

                pref       = getSharedPreferences(session, MODE_PRIVATE);

                boolean username = user.equals(pref.getString("username", "null"));
                boolean password =  pass.equals(pref.getString("password", "null"));
                if(username && password)
                {
                    Intent moveHalamanUtama = new Intent(MainActivity.this, HalamanUtama.class);
                    startActivity(moveHalamanUtama);
                }else{
                    Toast.makeText(MainActivity.this, "Username dan Password SALAH !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mRegistrasiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveRegis = new Intent(MainActivity.this,Registrasi.class);
                startActivity(moveRegis);
            }
        });

    }
}