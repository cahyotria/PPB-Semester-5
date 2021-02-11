package com.example.hitungbangunan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Persegi extends AppCompatActivity {

    private EditText mSisiPersegi;
    private TextView mHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persegi);

        mSisiPersegi = (EditText) findViewById(R.id.editTextSisiPersegi);
        mHasil = (TextView) findViewById(R.id.HasilPersegi);
    }

    private static Double getOperand(EditText operandEditText){
        String operangText = getOperandText(operandEditText);
        return Double.valueOf(operangText);
    }

    private static String getOperandText(EditText operangEditText){
        return operangEditText.getText().toString();
    }

    public void hitungLuas(View view) {
        Double operandSisi;
        String Result;
        operandSisi = getOperand(mSisiPersegi);
        Result = String.valueOf(operandSisi * operandSisi);
        mHasil.setText(Result);
    }

    public void hitungKeliling(View view) {
        Double operandSisi;
        String Result;
        operandSisi = getOperand(mSisiPersegi);
        Result = String.valueOf(operandSisi * 4);
        mHasil.setText(Result);
    }
}