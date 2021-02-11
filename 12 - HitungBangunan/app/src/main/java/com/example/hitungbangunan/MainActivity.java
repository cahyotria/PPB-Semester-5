package com.example.hitungbangunan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showPersegi(View view) {
        Intent intent = new Intent(this, Persegi.class);
        startActivity(intent);
    }

    public void showSegitiga(View view) {
        Intent intent = new Intent(this, Segitiga.class);
        startActivity(intent);
    }

    public void showKubus(View view) {
        Intent intent = new Intent (this, Kubus.class);
        startActivity(intent);
    }

    public void showLingkaran(View view) {
        Intent intent = new Intent(this, Lingkaran.class);
        startActivity(intent);
    }
}