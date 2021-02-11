package com.example.hitungbangunan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Lingkaran extends AppCompatActivity {

    private EditText mJariJari;
    private TextView mHasilLingkaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lingkaran);

        mJariJari = (EditText) findViewById(R.id.editTextJariJari);
        mHasilLingkaran = (TextView) findViewById(R.id.HasilLingkaran);
    }

    private static Double getOperand(EditText operandEditText){
        String operangText = getOperandText(operandEditText);
        return Double.valueOf(operangText);
    }

    private static String getOperandText(EditText operangEditText){
        return operangEditText.getText().toString();
    }

    public void hitungLuas(View view) {
        Double operandJariJari;
        String Result;

        operandJariJari = getOperand(mJariJari);

        Result = String.valueOf(operandJariJari * operandJariJari * 3.14 );

        mHasilLingkaran.setText(Result);
    }

    public void hitungKeliling(View view) {
        Double operandJariJari;
        String Result;

        operandJariJari = getOperand(mJariJari);

        Result = String.valueOf(2 * operandJariJari * 3.14);

        mHasilLingkaran.setText(Result);
    }
}