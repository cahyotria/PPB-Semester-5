package com.example.ta_zantech;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrasiActivity extends Activity {
    ProgressDialog progressDialog;
    Button mBtnBack, mBtnRegis;

    EditText mRegisNama, mRegisUsername, mRegisPassword, mRegisEmail, mRegisNohp, mRegisAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        mBtnBack    = findViewById(R.id.btnBack);
        mBtnRegis   = findViewById(R.id.btnRegistrasi);

        mRegisNama  = findViewById(R.id.regisNama);
        mRegisUsername  = findViewById(R.id.regisUsername);
        mRegisPassword  = findViewById(R.id.regisPassword);
        mRegisEmail  = findViewById(R.id.regisEmail);
        mRegisNohp  = findViewById(R.id.regisNohp);
        mRegisAlamat  = findViewById(R.id.regisAlamat);

        progressDialog = new ProgressDialog(this);

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveLogin = new Intent(RegistrasiActivity.this, LoginActivity.class);
                startActivity(moveLogin);
            }
        });

        mBtnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nama = mRegisNama.getText().toString();
                String Username = mRegisUsername.getText().toString();
                String Password = mRegisPassword.getText().toString();
                String Email = mRegisEmail.getText().toString();
                String NoHp = mRegisNohp.getText().toString();
                String Alamat = mRegisAlamat.getText().toString();

                if(Nama != "" && Username != "" && Password != "" && Email != "" && NoHp != "" && Alamat != ""){
                    checkRegistrasi(Nama, Username, Password, Email, NoHp, Alamat);
                }

            }
        });

    }
    private void checkRegistrasi (String Nama, String Username, String Password, String Email, String NoHp, String Alamat){
        if(checkConnection()){
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    Server.SERVER_REGISTRASI,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String respon = jsonObject.getString("server_respon");

                                if (respon.equals("[{\"status\":\"OK\"}]")) {
                                    Toast.makeText(RegistrasiActivity.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                                    Intent moveLogin = new Intent(RegistrasiActivity.this, LoginActivity.class);
                                    startActivity(moveLogin);
                                } else {
                                    Toast.makeText(RegistrasiActivity.this, "Silahakan Diulangi", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(RegistrasiActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("nama", Nama);
                    params.put("username", Username);
                    params.put("password", Password);
                    params.put("email", Email);
                    params.put("no_hp", NoHp);
                    params.put("alamat", Alamat);
                    return params;
                }
            };

            VolleyConnection.getInstance(RegistrasiActivity.this).addToRequestQue(stringRequest);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.cancel();
                }
            }, 2000);
        }else{
            Toast.makeText(this, "Tidak Ada Koneksi", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}