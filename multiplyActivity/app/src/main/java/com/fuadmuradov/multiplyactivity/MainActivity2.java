package com.fuadmuradov.multiplyactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
   TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textview = findViewById(R.id.textView3);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        textview.setText(username);
    }

    public void change2(View view){
        Intent intent =new Intent(getApplicationContext(), MainActivity.class);

        startActivity(intent);
    }
}