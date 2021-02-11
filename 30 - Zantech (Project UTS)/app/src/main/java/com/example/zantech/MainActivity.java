package com.example.zantech;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.BoringLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText mUsernameEdit, mPasswordEdit;
    Button mLoginButton, mRegisButton;

    private static final String SESSION = "session";

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsernameEdit   = (EditText) findViewById(R.id.editUsernameLogin);
        mPasswordEdit   = (EditText) findViewById(R.id.editPasswordLogin);

        mRegisButton    =  (Button) findViewById(R.id.btnRegistasi);
        mLoginButton    =  (Button) findViewById(R.id.btnLogin);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref    = getSharedPreferences(SESSION, MODE_PRIVATE);
                Boolean user = mUsernameEdit.getText().toString().equals(pref.getString("username", "NotFound"));
                Boolean password = mPasswordEdit.getText().toString().equals(pref.getString("password", "NotFound"));

                if(user && password){
                    Intent moveDashboard = new Intent(MainActivity.this, DashboardActivity.class);
                    startActivity(moveDashboard);
                }else{
                    Toast.makeText(MainActivity.this, "Username & Password SALAH", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mRegisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveRegis = new Intent(MainActivity.this, RegistrasiActivity.class);
                startActivity(moveRegis);
            }
        });


    }
}