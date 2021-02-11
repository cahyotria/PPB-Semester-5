package com.example.a09_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Calculator mCalculator;

    private EditText mAngka1EditText;
    private EditText mAngka2EditText;
    private TextView mHasilTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCalculator = new Calculator();
        mAngka1EditText = (EditText) findViewById(R.id.editAngka1);
        mAngka2EditText = (EditText) findViewById(R.id.editAngka2);
        mHasilTextView = (TextView) findViewById(R.id.textHasilCal);
    }

    private static String getAngkaText(EditText angkaText){
        return angkaText.getText().toString();
    }

    private static double getAngka(EditText angkaText){
        String angka = getAngkaText(angkaText);
        return Double.valueOf(angka);
    }

    private void komputasi(Calculator.Operator operator){
        double inputSatu;
        double inputDua;
        String Hasil;

        inputSatu = getAngka(mAngka1EditText);
        inputDua = getAngka(mAngka2EditText);

        switch (operator){
            case Tambah:
                Hasil = String.valueOf(mCalculator.tambah(inputSatu,inputDua));
                break;
            case Kurang:
                Hasil = String.valueOf(mCalculator.kurang(inputSatu, inputDua));
                break;
            case Bagi:
                Hasil = String.valueOf(mCalculator.bagi(inputSatu,inputDua));
                break;
            case Kali:
                Hasil = String.valueOf(mCalculator.kali(inputSatu,inputDua));
                break;
            default:
                Hasil = "0";
                break;
        }
        mHasilTextView.setText(Hasil);
    }

    public void onTambah(View view) {
        komputasi(Calculator.Operator.Tambah);
    }

    public void onKurang(View view) {
        komputasi(Calculator.Operator.Kurang);
    }

    public void onBagi(View view) {
        komputasi(Calculator.Operator.Bagi);
    }

    public void onKali(View view) {
        komputasi(Calculator.Operator.Kali);
    }
}