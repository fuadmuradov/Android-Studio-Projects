package com.fuadmuradov.runnablehandler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
 Runnable runnable;
 Handler handler;
 TextView textview;
 int number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = findViewById(R.id.textView);
        number =0;



    }

    public void start(View view){
        handler =new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                textview.setText("Number: " + number);
                number++;
                handler.postDelayed(runnable, 1000);

            }
        };
  handler.post(runnable);


    }
    public void stop(View view){
    handler.removeCallbacks(runnable);
    }



}