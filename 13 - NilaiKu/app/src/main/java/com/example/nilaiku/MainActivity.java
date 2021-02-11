package com.example.nilaiku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText edtNTugas,edtFinalNTugas, edtNUTS, edtFinalNUTS, edtNUAS, edtFinalNUAS, edtNAkhir, edtNHuruf, edtNPredikat;
    private float persenTugas = 0.3f;
    private float persenUTS = 0.35f;
    private float persenUAS = 0.35f;
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Pendeklarasian
        edtNTugas = (EditText) findViewById(R.id.editNTugas);
        edtNUTS = (EditText) findViewById(R.id.editNUTS);
        edtNUAS = (EditText) findViewById(R.id.editNUAS);

        edtFinalNTugas = (EditText) findViewById(R.id.finalNTugas);
        edtFinalNUTS   = (EditText) findViewById(R.id.finalNUTS);
        edtFinalNUAS   = (EditText) findViewById(R.id.finalNUAS);

        edtNAkhir = (EditText) findViewById(R.id.editNAkhir);
        edtNHuruf = (EditText) findViewById(R.id.editNHuruf);
        edtNPredikat = (EditText) findViewById(R.id.editPredikat);


    }

    private static String getNilaiToString (EditText nilaiText){
        return nilaiText.getText().toString();
    }

    private static Float getNilai(EditText nilaiText){
        String nilai = getNilaiToString(nilaiText);
        return Float.valueOf(nilai);
    }

    private static Float hitungNilaiAwal (Float operandNilai, Float persen){
        Float ResultAwal = operandNilai * persen;
        return ResultAwal;
    }

    private static Float nilaiAkhir (float nTugas, float nUTS, float nUAS){
        return nTugas + nUTS + nUAS;
    }

    private static String getHuruf(float nAkhir){
        String huruf;

        if(nAkhir >= 85 && nAkhir <= 100){
            huruf = "A";
        }else if(nAkhir >= 80 && nAkhir <= 84){
            huruf =  "AB";
        }else if(nAkhir >= 70 && nAkhir <= 79){
            huruf = "B";
        }else if(nAkhir >= 65 && nAkhir <= 69){
            huruf = "BC";
        }else{
            huruf = "C";
        }

        return huruf;
    }

    private static String getPredikat(String huruf){
        String predikat;

        switch (huruf){
            case "A": predikat = "Apik"; break;
            case "AB": predikat = "Apik Baik";break;
            case "B": predikat = "Baik"; break;
            case "BC": predikat = "Cukup Baik"; break;
            case "C": predikat = "Cukup"; break;
            default: predikat = "Jelek";
        }
        return predikat;
    }


    public void calHitung(View view) {
        Float operandTugas, operandUTS, operandUAS;

        //Pengambilan Nilai yang sudah diubah menjadi FLOAT
        operandTugas = getNilai(edtNTugas);
        operandUTS = getNilai(edtNUTS);
        operandUAS = getNilai(edtNUAS);

        //Mode Hitung
        Float awalTugas = hitungNilaiAwal(operandTugas, persenTugas);
        Float awalUTS = hitungNilaiAwal(operandUTS, persenUTS);
        Float awalUAS = hitungNilaiAwal(operandUAS, persenUAS);

        edtFinalNTugas.setText(String.valueOf(df.format(awalTugas)));
        edtFinalNUTS.setText(String.valueOf(df.format(awalUTS)));
        edtFinalNUAS.setText(String.valueOf(df.format(awalUAS)));

        //Hitung Nilai Akhir
        edtNAkhir.setText(String.valueOf(nilaiAkhir(awalTugas,awalUTS,awalUAS)));
        edtNHuruf.setText(getHuruf(nilaiAkhir(awalTugas,awalUTS,awalUAS)));
        edtNPredikat.setText(getPredikat(getHuruf(nilaiAkhir(awalTugas,awalUTS,awalUAS))));

    }

}