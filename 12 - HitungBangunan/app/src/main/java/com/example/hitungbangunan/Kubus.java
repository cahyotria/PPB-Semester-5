package com.example.hitungbangunan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Kubus extends AppCompatActivity {

    private EditText mSisiKubus;
    private TextView mHasilKubus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kubus);

        mSisiKubus = (EditText) findViewById(R.id.editTextSisiKubus);
        mHasilKubus = (TextView) findViewById(R.id.HasilKubus);
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

        operandSisi = getOperand(mSisiKubus);
        Result = String.valueOf(operandSisi * operandSisi * 6);

        mHasilKubus.setText(Result);
    }
    
    public void hitungKeliling(View view) {
        Double operandSisi;
        String Result;

        operandSisi = getOperand(mSisiKubus);
        Result = String.valueOf(operandSisi * 12);

        mHasilKubus.setText(Result);

    }
}