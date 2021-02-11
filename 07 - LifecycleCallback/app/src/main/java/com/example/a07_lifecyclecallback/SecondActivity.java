package com.example.a07_lifecyclecallback;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {
    public static final String EXTRA_REPLAY = "com.example.android.twoactivitieslifecycle.extra.REPLY";
    private static final String LOG_TAG = SecondActivity.class.getSimpleName();

    private EditText mReply = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mReply = (EditText) findViewById(R.id.editText_second);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView textView = (TextView) findViewById(R.id.text_message);
        if(textView != null){
            textView.setText(message);
        }

        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");
    }


    public void returnReply(View view) {
        String reply = mReply.getText().toString();

        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLAY, reply);
        setResult(RESULT_OK, replyIntent);

        Log.d(LOG_TAG, "End Second Acitivity");
        finish();
    }

    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    public void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }
}