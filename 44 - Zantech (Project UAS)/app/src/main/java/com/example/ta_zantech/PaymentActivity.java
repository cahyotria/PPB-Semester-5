package com.example.ta_zantech;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class PaymentActivity extends Activity {

    EditText mEditTotal, mEditBayar;
    TextView mNotaTotal, mNotaCash, mNotaKembalian;

    Button mBtnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        mEditTotal = findViewById(R.id.editTotal);
        mEditBayar = findViewById(R.id.editBayar);

        mNotaTotal = findViewById(R.id.edTotal);
        mNotaCash = findViewById(R.id.edBayar);
        mNotaKembalian = findViewById(R.id.edKembalian);

        mBtnSubmit = findViewById(R.id.btnBayar);

        Integer Total = getIntent().getIntExtra("total", 0);
        mEditTotal.setText(formatRupiah(Total));

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNotaTotal.setText(formatRupiah(Total));
                mNotaCash.setText(formatRupiah(Integer.parseInt(mEditBayar.getText().toString())));
                Integer hasil = Integer.parseInt(mEditBayar.getText().toString()) - Total;
                mNotaKembalian.setText(formatRupiah(hasil));
            }
        });

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