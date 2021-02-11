package com.example.zantech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class UpdateActivity extends AppCompatActivity {

    private Button mUpdateButton, mCancelButton;
    private EditText mUsername, mPass1, mPass2;

    private static final String SESSION = "session";
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        mUsername       = (EditText) findViewById(R.id.editUsernameUpdate);
        mPass1          = (EditText) findViewById(R.id.editPassOldUpdate);
        mPass2          = (EditText) findViewById(R.id.editPassNowUpdate);

        mUpdateButton   = (Button) findViewById(R.id.btnUpdate);
        mCancelButton   = (Button) findViewById(R.id.btnCancel);

        pref            = getSharedPreferences(SESSION, MODE_PRIVATE);
        editor          = pref.edit();

        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean bandingPass = pref.getString("password","").equals(mPass1.getText().toString());
                boolean bandingUser = pref.getString("username", "").equals(mUsername.getText().toString());

                if(bandingPass && bandingUser){
                    editor.putString("username", mUsername.getText().toString());
                    editor.putString("password", mPass2.getText().toString());
                    editor.apply();
                    Toast.makeText(UpdateActivity.this, "Update Berhasil", Toast.LENGTH_LONG).show();
                }else{
                    mUsername.setText("");
                    mPass1.setText("");
                    mPass2.setText("");
                    Toast.makeText(UpdateActivity.this, "Username dan Password anda SALAH", Toast.LENGTH_LONG).show();
                }
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUsername.setText("");
                mPass1.setText("");
                mPass2.setText("");

                Intent moveDashboard = new Intent(UpdateActivity.this, DashboardActivity.class);
                startActivity(moveDashboard);
            }
        });
    }
}