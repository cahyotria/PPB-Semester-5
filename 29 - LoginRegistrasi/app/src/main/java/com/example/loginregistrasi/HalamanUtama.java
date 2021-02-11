package com.example.loginregistrasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HalamanUtama extends AppCompatActivity {

    TextView mSambutanText;
    Button mLogoutButton;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public static final String session = "session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_utama);

        mSambutanText   = (TextView) findViewById(R.id.textSambutan);

        mLogoutButton   = (Button) findViewById(R.id.btnLogout);

        pref            = getSharedPreferences(session, MODE_PRIVATE);
        String sambutan = pref.getString("namaLengkap", "notFound");

        mSambutanText.setText("Selamat Datang, " + sambutan);

        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = pref.edit();
                editor.remove("namaLengkap");
                editor.remove("username");
                editor.remove("password");
                editor.apply();

                Intent moveMain = new Intent(HalamanUtama.this, MainActivity.class);
                startActivity(moveMain);
            }
        });
    }
}