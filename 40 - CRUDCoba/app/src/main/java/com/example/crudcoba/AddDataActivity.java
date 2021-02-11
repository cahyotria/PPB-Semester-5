package com.example.crudcoba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddDataActivity extends AppCompatActivity {

    EditText txtName, txtEmail, txtContact, txtAddress;
    Button InsertBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        txtName         = findViewById(R.id.editNama);
        txtEmail        = findViewById(R.id.editEmail);
        txtContact      = findViewById(R.id.editContact);
        txtAddress      = findViewById(R.id.editAddress);

        InsertBtn       = findViewById(R.id.btnInsert);

        InsertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

    }

    private void insertData() {
        String name = txtName.getText().toString().trim();
        String email = txtEmail.getText().toString().trim();
        String contact = txtContact.getText().toString().trim();
        String address = txtAddress.getText().toString().trim();

        if(name.isEmpty() && email.isEmpty() && contact.isEmpty() && address.isEmpty()){
            Toast.makeText(this, "Tolong Di Isi", Toast.LENGTH_SHORT).show();
            return;
        }else{
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    "http://192.168.1.8/tugas/cobarestapi/insert.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.equalsIgnoreCase("Data Inserted")){
                                Toast.makeText(AddDataActivity.this, "Data Berhasil di Input", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(AddDataActivity.this, response, Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AddDataActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("name", name);
                    params.put("email", email);
                    params.put("contact", contact);
                    params.put("address", address);

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(AddDataActivity.this);
            requestQueue.add(stringRequest);
        }

    }
}