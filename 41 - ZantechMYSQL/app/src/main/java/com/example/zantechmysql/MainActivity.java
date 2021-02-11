package com.example.zantechmysql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    MyAdapter adapter;

    public static ArrayList<DataBarang> dataBarangArrayList = new ArrayList<>();
    String url = "http://192.168.1.8/tugas/myadmin_contoh/read.php";

    DataBarang dataBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.myListView);

        adapter = new MyAdapter(MainActivity.this, dataBarangArrayList);
        listView.setAdapter(adapter);

        retrieveData();
    }

    private void retrieveData() {
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        dataBarangArrayList.clear();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if(success.equals("1")){
                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String kode_barang  = object.getString("kode_barang");
                                    String nama_barang  = object.getString("nama_barang");
                                    String satuan       = object.getString("satuan");
                                    Integer harga_jual  = object.getInt("harga_jual");
                                    Integer harga_beli  = object.getInt("harga_beli");
                                    Integer stok        = object.getInt("stok");
                                    Integer stok_min    = object.getInt("stok_min");

                                    dataBarang = new DataBarang(kode_barang,nama_barang,satuan,harga_jual,harga_beli,stok,stok_min);
                                    dataBarangArrayList.add(dataBarang);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}