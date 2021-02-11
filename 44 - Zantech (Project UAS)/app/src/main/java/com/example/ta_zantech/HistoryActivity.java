package com.example.ta_zantech;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HistoryActivity extends Activity {

    public static ArrayList<History> dataHistoryArrayList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private HistoryAdapter mHistoryAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mRecyclerView = findViewById(R.id.MyRVHistory);

        getDataHistory();
    }

    public void getDataHistory(){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Server.SERVER_HISTORY,
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
                                    String id_history = data.getString("id");
                                    String nama_produk = data.getString("nama_produk");
                                    String harga = data.getString("harga");
                                    String tanggal = data.getString("tanggal");
                                    String jam = data.getString("jam");

                                    History history = new History(id_history, nama_produk, harga, tanggal, jam);
                                    dataHistoryArrayList.add(history);
                                }
                                mLayoutManager = new LinearLayoutManager(HistoryActivity.this);
                                mHistoryAdapter = new HistoryAdapter(HistoryActivity.this, dataHistoryArrayList);

                                mRecyclerView.setLayoutManager(mLayoutManager);
                                mRecyclerView.setAdapter(mHistoryAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HistoryActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params= new HashMap<>();
                pref = getSharedPreferences(Server.SESSION, MODE_PRIVATE);
                String id = pref.getString("id_konsumen", "notfound");
                params.put("id_konsumen", id);
                return params;
            }
        };

        VolleyConnection.getInstance(HistoryActivity.this).addToRequestQue(stringRequest);
    }
}