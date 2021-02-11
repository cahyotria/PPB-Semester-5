package com.example.ta_zantech;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity {

    ProgressDialog progressDialog;

    Button mBtnRegis, mBtnLogin;
    EditText mLoginUser, mLoginPass;

    String idKonsumen, namaKonsumen;

    SharedPreferences pref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mBtnLogin   = findViewById(R.id.btnLogin);
        mBtnRegis   = findViewById(R.id.btnRegistrasi);

        mLoginUser  = findViewById(R.id.loginUsername);
        mLoginPass  = findViewById(R.id.loginPassword);

        progressDialog = new ProgressDialog(this);

        mBtnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveRegis = new Intent(LoginActivity.this, RegistrasiActivity.class);
                startActivity(moveRegis);
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = mLoginUser.getText().toString();
                String Password = mLoginPass.getText().toString();

                if(Username != "" && Password != ""){
                    checkLogin(Username, Password);
                }
            }
        });
    }

    private void checkLogin(String Username, String Password){
        if(checkConnection()){
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    Server.SERVER_LOGIN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("success");
                                JSONArray jsonArray = jsonObject.getJSONArray("data");

                                if(success.equals("1")){
                                    //Mengambil Data JSON
                                    for(int i = 0; i < jsonArray.length(); i++){
                                        JSONObject data = jsonArray.getJSONObject(i);
                                        idKonsumen = data.getString("id");
                                        namaKonsumen = data.getString("nama");
                                    }

                                    //Membuat SESSION untuk Transaksi
                                    pref    = getSharedPreferences(Server.SESSION, MODE_PRIVATE);
                                    editor  = pref.edit();

                                    editor.putString("id_konsumen", idKonsumen);
                                    editor.putString("nama_konsumen", namaKonsumen);
                                    editor.apply();

                                    //Pindah ke Activity Dashboard
                                    Toast.makeText(LoginActivity.this, "Login Berhasil" , Toast.LENGTH_SHORT).show();
                                    Intent moveDashboard = new Intent(LoginActivity.this, DashboardActivity.class);
                                    startActivity(moveDashboard);
                                }

                                if(success.equals("0")){
                                    Toast.makeText(LoginActivity.this, "Username / Password Salah", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", Username);
                    params.put("password", Password);
                    return params;
                }
            };

            VolleyConnection.getInstance(LoginActivity.this).addToRequestQue(stringRequest);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.cancel();
                }
            }, 1500);
        }else{
            Toast.makeText(LoginActivity.this, "Tidak Ada Koneksi", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean checkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


}