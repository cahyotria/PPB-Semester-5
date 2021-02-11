package com.example.zantech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class PaymentActivity extends AppCompatActivity {

    private TextView mTotalText,mKembalianText;
    private EditText mDibayarEdit;
    private Button mHitungButton;
    private Button mBayarButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        mTotalText      = (TextView) findViewById(R.id.textTotal);
        mKembalianText  = (TextView) findViewById(R.id.textKembalian);
        mDibayarEdit    = (EditText) findViewById(R.id.editJumlahPembayaran);
        mBayarButton    = (Button) findViewById(R.id.btnBayar);
        
        mHitungButton   = (Button) findViewById(R.id.btnHitung);

        final Integer Total = getIntent().getIntExtra("totalPrice",0);
        mTotalText.setText(formatIDR(Total));
        
        mHitungButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer dibayar = Integer.parseInt(mDibayarEdit.getText().toString());
                Integer hitungKembali = dibayar - Total;

                mKembalianText.setText(formatIDR(hitungKembali));

            }
        });
        
        mBayarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PaymentActivity.this, "PEMBAYARAN BERHASIL", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String formatIDR(int total){
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        return kursIndonesia.format(total);
    }


}