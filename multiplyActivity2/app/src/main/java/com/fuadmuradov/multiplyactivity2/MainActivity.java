package com.fuadmuradov.multiplyactivity2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = findViewById(R.id.textView);
        int i=0;
        if(i==0)
        Toast.makeText(getApplicationContext(), "Counter Started", Toast.LENGTH_LONG).show();
        i++;
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textview.setText("Left: " + millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "Done!", Toast.LENGTH_LONG).show();
                textview.setText("Finished!!!");
            }
        }.start();



    }
}