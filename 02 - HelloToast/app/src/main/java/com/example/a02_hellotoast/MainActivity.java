package com.example.a02_hellotoast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;
    private TextView mShowCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.show_count);
    }

    public void countUp(View view){
        mCount++;
        if(mShowCount != null){
            mShowCount.setText(Integer.toString(mCount));
        }
    }

    public void resetUp(View view) {
        mCount = 0;
        mShowCount.setText(Integer.toString(mCount));
    }

    public void showToast(View view){
        Context context = this;
        Toast toast = Toast.makeText(context, R.string.toast, Toast.LENGTH_SHORT);
        toast.show();
    }

}