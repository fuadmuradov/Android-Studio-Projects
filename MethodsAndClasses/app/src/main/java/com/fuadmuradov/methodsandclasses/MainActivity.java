package com.fuadmuradov.methodsandclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("on create called");
        testmethods();
        System.out.println(testter(3,5));
        makeMusician();
    }

    public void makeMusician(){
        Musicians james =new Musicians("Fuad", "tar", 22);
        System.out.println("Orxan qarayev");


    }


    public void testmethods(){
        int x= 5+3;
        System.out.println("value x = " + x);
    }

    public int testter(int a, int b){
        return a+b;
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("on resume called");

    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("on stop called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("on pause called");
    }
}