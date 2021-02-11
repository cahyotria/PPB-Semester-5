package com.example.ta_zantech;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateUserActivity extends Activity {

    EditText mEditNama, mEditUsername, mEditPassword, mEditEmail, mEditNoHP, mEditAlamat;
    Button mBtnUpdate;

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        mEditNama           = findViewById(R.id.profileNama);
        mEditUsername       = findViewById(R.id.profileUsername);
        mEditPassword       = findViewById(R.id.profilePassword);
        mEditEmail          = findViewById(R.id.profileEmail);
        mEditNoHP           = findViewById(R.id.profileNoHP);
        mEditAlamat         = findViewById(R.id.profileAlamat);

        mBtnUpdate          = findViewById(R.id.btnUpdate);
        
        getUser();
        
        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref = getSharedPreferences(Server.SESSION, MODE_PRIVATE);
                String id = pref.getString("id_konsumen", "notfound");
                String nama = mEditNama.getText().toString();
                String username = mEditUsername.getText().toString();
                String password = mEditPassword.getText().toString();
                String email = mEditEmail.getText().toString();
                String no_hp = mEditNoHP.getText().toString();
                String alamat = mEditAlamat.getText().toString();
                UpdateProfile(id, nama, username, password, email, no_hp, alamat);
                Toast.makeText(UpdateUserActivity.this, "Update BERHASIL", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void UpdateProfile(String id, String nama, String username, String password, String email, String no_hp, String alamat){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Server.SERVER_UPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if(success.equals("1")){
                                Toast.makeText(UpdateUserActivity.this, "Update BERHASIL", Toast.LENGTH_SHORT).show();
                            }
                            if(success.equals("0")){
                                Toast.makeText(UpdateUserActivity.this, "Update GAGAL", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UpdateUserActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("nama_konsumen", nama);
                params.put("username", username);
                params.put("password", password);
                params.put("email", email);
                params.put("no_hp", no_hp);
                params.put("alamat", alamat);
                return params;
            }
        };
        VolleyConnection.getInstance(UpdateUserActivity.this).addToRequestQue(stringRequest);
    }


    private void getUser(){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Server.SERVER_UPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if(success.equals("1")){
                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject data = jsonArray.getJSONObject(i);
                                    String nama = data.getString("nama_konsumen");
                                    String username = data.getString("username");
                                    String password = data.getString("password");
                                    String email = data.getString("email");
                                    String no_hp    = data.getString("no_hp");
                                    String alamat   = data.getString("alamat");

                                    setFieldProfile(nama,username, email,password, no_hp, alamat);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UpdateUserActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams(){
                pref = getSharedPreferences(Server.SESSION, MODE_PRIVATE);
                String id_konsumen = pref.getString("id_konsumen", "notfound");
                Map<String, String> params = new HashMap<>();
                params.put("id_konsumen", id_konsumen);
                return params;
            }
        };

        VolleyConnection.getInstance(UpdateUserActivity.this).addToRequestQue(stringRequest);
    }

    private void setFieldProfile(String nama, String username, String email, String password, String no_hp, String alamat){
        mEditNama.setText(nama);
        mEditUsername.setText(username);
        mEditPassword.setText(password);
        mEditEmail.setText(email);
        mEditNoHP.setText(no_hp);
        mEditAlamat.setText(alamat);
    }

}