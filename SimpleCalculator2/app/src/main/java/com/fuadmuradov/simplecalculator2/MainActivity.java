package com.fuadmuradov.simplecalculator2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText number1;
    EditText number2;
    TextView resultView;
    int number11;
    int number22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number1=findViewById(R.id.editTextTextPersonName);
        number2=findViewById(R.id.editTextTextPersonName2);
        resultView=findViewById(R.id.textView);



    }

    public void Minus(View view){


        resultView.setText("Result: " + (number11-number22));
    }

    public void Plus(View view)

    {
        number11 = Integer.parseInt(number1.getText().toString());
        number22 = Integer.parseInt(number2.getText().toString());
        resultView.setText("Result: " + (number11+number22));
    }

    public void multiply(View view){
        resultView.setText("Result: " + (number11*number22));
    }

    public void divited(View view){
        resultView.setText("Result: " + (number11/number22));
    }

}