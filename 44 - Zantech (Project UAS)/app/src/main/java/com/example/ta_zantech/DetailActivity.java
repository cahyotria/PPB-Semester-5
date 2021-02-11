package com.example.ta_zantech;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class DetailActivity extends Activity {

    private TextView mDetailTitle, mDetailPrice, mDetailDeskripsi;
    private ImageView mDetailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mDetailTitle = findViewById(R.id.titleDetail);
        mDetailPrice = findViewById(R.id.priceDetail);
        mDetailDeskripsi = findViewById(R.id.deskripsiDetail);
        mDetailImage = findViewById(R.id.imageDetail);

        getIntentDashboard();
    }

    public void getIntentDashboard() {
        boolean title = getIntent().hasExtra("namaDetail");
        boolean image = getIntent().hasExtra("gambarDetail");
        boolean price = getIntent().hasExtra("hargaDetail");
        boolean deskripsi = getIntent().hasExtra("deskripsiDetail");

        if (title && image && price && deskripsi) {
            String mTitle = getIntent().getStringExtra("namaDetail");
            String mImage = getIntent().getStringExtra("gambarDetail");
            String mPrice = getIntent().getStringExtra("hargaDetail");
            String mDeskripsi = getIntent().getStringExtra("deskripsiDetail");

            setDataDetail(mTitle, mImage, mPrice, mDeskripsi);
        }
    }

    public void setDataDetail(String title, String image, String price, String deskripsi) {
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        Integer harga = Integer.parseInt(price);

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        Glide.with(DetailActivity.this).load(image).into(mDetailImage);
        mDetailTitle.setText(title);
        mDetailPrice.setText(String.valueOf(kursIndonesia.format(harga)));
        mDetailDeskripsi.setText(deskripsi);

    }
}