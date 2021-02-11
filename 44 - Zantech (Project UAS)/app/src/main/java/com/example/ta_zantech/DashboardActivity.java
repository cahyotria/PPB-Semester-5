package com.example.ta_zantech;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.ta_zantech.Server.SESSION;

public class DashboardActivity extends AppCompatActivity {

    private int Total = 0;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    TextView mTextTotal;

    public static ArrayList<Produk> dataProdukArrayList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.MyRecyclerView);
        mTextTotal = findViewById(R.id.textTotal);

        //Mengambil data Konsumen
        getPrefKonsumen();

        //Mengambil Daftar Produk melalui JSON
        getProduk();

        mTextTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent movePayment = new Intent(DashboardActivity.this, PaymentActivity.class);
                movePayment.putExtra("total", Total);
                startActivity(movePayment);
            }
        });
    }

    public void getPrefKonsumen(){
        pref = getSharedPreferences(SESSION, MODE_PRIVATE);
        String Welcome = pref.getString("nama_konsumen", "notfound");

        Toast.makeText(DashboardActivity.this, "Selamat Berbelanja, " + Welcome , Toast.LENGTH_SHORT).show();
    }

    public void getProduk(){

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Server.SERVER_DASHBOARD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if(success.equals("1")){
                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String id_produk = object.getString("id");
                                    String kode_produk = object.getString("kode_produk");
                                    String nama_produk = object.getString("nama_produk");
                                    String harga = object.getString("harga");
                                    String stok = object.getString("stok");
                                    String deskripsi = object.getString("deskripsi");
                                    String image = object.getString("gambar");

                                    Produk produk = new Produk(
                                            id_produk, kode_produk,
                                            nama_produk, harga,
                                            stok, deskripsi, image);
                                    dataProdukArrayList.add(produk);
                                }
                                BuildRecyclerView(dataProdukArrayList);
                            }

                            if(success.equals("0")){
                                Toast.makeText(DashboardActivity.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DashboardActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void BuildRecyclerView(ArrayList<Produk> dataList){
        mLayoutManager  = new GridLayoutManager(DashboardActivity.this, 2);
        mAdapter        = new RecyclerAdapter(DashboardActivity.this, dataList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onTitleClick(int position) {
                Intent moveDetail = new Intent(DashboardActivity.this, DetailActivity.class);
                moveDetail.putExtra("idDetail", dataList.get(position).getId_produk());
                moveDetail.putExtra("namaDetail", dataList.get(position).getNama_produk());
                moveDetail.putExtra("gambarDetail", dataList.get(position).getImage());
                moveDetail.putExtra("hargaDetail", dataList.get(position).getHarga());
                moveDetail.putExtra("deskripsiDetail", dataList.get(position).getDeskripsi());
                startActivity(moveDetail);
            }

            @Override
            public void onImageClick(int position) {
                Total = Total + Integer.parseInt(dataList.get(position).getHarga());
                mTextTotal.setText(String.valueOf(formatRupiah(Total)));

                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST,
                        Server.SERVER_TRANSAKSI,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(DashboardActivity.this, "Transaksi Berhasil Tersimpan", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(DashboardActivity.this, "Transaksi Gagal Tersimpan", Toast.LENGTH_SHORT).show();
                            }
                        }
                ){
                    @Override
                    protected Map<String, String> getParams(){
                        pref = getSharedPreferences(SESSION, MODE_PRIVATE);
                        String id = pref.getString("id_konsumen", "0");

                        Map<String, String> params = new HashMap<>();
                        params.put("id_konsumen",id);
                        params.put("id_produk", dataList.get(position).getId_produk());
                        return params;
                    }
                };

                VolleyConnection.getInstance(DashboardActivity.this).addToRequestQue(stringRequest);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.item_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuHistory:
                Intent moveHistory = new Intent(DashboardActivity.this, HistoryActivity.class);
                startActivity(moveHistory);
                break;
            case R.id.menuCallCenter:
                String TelpNo = "081234567890";
                Intent telpIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + TelpNo));
                startActivity(telpIntent);
                break;
            case R.id.menuSMSCenter:
                String phoneNo = "081234567890";
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNo));
                startActivity(smsIntent);
                break;
            case R.id.menuLokasi:
                String location = "JL Candi Mutiara Timur I";
                Uri uri = Uri.parse("geo:0,0?q="+ location);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
                break;
            case R.id.menuUpdate:
                Intent moveUpdateUser = new Intent(DashboardActivity.this, UpdateUserActivity.class);
                startActivity(moveUpdateUser);
                break;
            case R.id.menuLogout:
                Toast.makeText(DashboardActivity.this, "Terima Kasih", Toast.LENGTH_SHORT).show();
                Intent moveLogin = new Intent(DashboardActivity.this, LoginActivity.class);
                editor = pref.edit();
                editor.clear().apply();
                startActivity(moveLogin);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public String formatRupiah(int total){
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        return kursIndonesia.format(total);

    }


}