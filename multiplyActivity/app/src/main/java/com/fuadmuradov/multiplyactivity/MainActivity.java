package com.fuadmuradov.multiplyactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
EditText edittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittext =  findViewById(R.id.editText1);

    }

    public void change1(View view){
        Intent intent =new Intent(MainActivity.this, MainActivity2.class);

        String username = edittext.getText().toString();
        intent.putExtra("username", username);
        startActivity(intent);
   }
}