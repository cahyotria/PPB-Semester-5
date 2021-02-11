package com.example.zantech;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class DetailActivity extends AppCompatActivity {
    private TextView mDetailTitle, mDetailPrice, mDetailDeskripsi;
    private ImageView mDetailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mDetailTitle     = (TextView) findViewById(R.id.titleDetail);
        mDetailPrice     = (TextView) findViewById(R.id.priceDetail);
        mDetailDeskripsi = (TextView) findViewById(R.id.deskripsiDetail);
        mDetailImage     = (ImageView) findViewById(R.id.imageDetail);

        getIntentDashboard();

    }

    public void getIntentDashboard(){
        boolean title = getIntent().hasExtra("titleItem");
        boolean image = getIntent().hasExtra("imageItem");
        boolean price = getIntent().hasExtra("priceItem");
        boolean deskripsi = getIntent().hasExtra("deskripsiItem");

        if(title && image && price && deskripsi){
            String mTitle   = getIntent().getStringExtra("titleItem");
            Integer mImage  = getIntent().getIntExtra("imageItem",0);
            Integer mPrice  = getIntent().getIntExtra("priceItem",0);
            String mDeskripsi  = getIntent().getStringExtra("deskripsiItem");

            setDataDetail(mTitle,mImage,mPrice,mDeskripsi);
        }
    }

    public void setDataDetail(String title, Integer image, Integer price, String deskripsi){
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        mDetailImage.setImageResource(image);
        mDetailTitle.setText(title);
        mDetailPrice.setText(String.valueOf(kursIndonesia.format(price)));
        mDetailDeskripsi.setText(deskripsi);

    }

}