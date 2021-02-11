package com.example.hitungbangunan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Segitiga extends AppCompatActivity {

    private EditText mAlasSegitiga;
    private EditText mTinggiSegitiga;
    private TextView mHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segitiga);

        mAlasSegitiga = (EditText) findViewById(R.id.editTextAlas);
        mTinggiSegitiga = (EditText) findViewById(R.id.editTextTinggi);
        mHasil = (TextView) findViewById(R.id.HasilSegitiga);
    }

    private static Double getOperand(EditText operandEditText){
        String operangText = getOperandText(operandEditText);
        return Double.valueOf(operangText);
    }

    private static String getOperandText(EditText operangEditText){
        return operangEditText.getText().toString();
    }

    public void hitungLuas(View view) {
        Double operandAlas;
        Double operandTinggi;
        String Result;

        operandAlas = getOperand(mAlasSegitiga);
        operandTinggi = getOperand(mTinggiSegitiga);

        Result = String.valueOf(0.5 * operandAlas * operandTinggi);

        mHasil.setText(Result);
    }

    public void hitungKeliling(View view) {
        Double operandAlas;
        String Result;

        operandAlas = getOperand(mAlasSegitiga);

        Result = String.valueOf(operandAlas * 3);

        mHasil.setText(Result);
    }
}