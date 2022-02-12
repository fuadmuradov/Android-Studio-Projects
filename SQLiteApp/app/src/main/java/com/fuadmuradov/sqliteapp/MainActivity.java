package com.fuadmuradov.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Musicians", MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians(name VARCHAR, age INT)");
            database.execSQL("INSERT INTO musicians(name, age) VALUES ('James', 50)",null);
            Cursor cursor = database.rawQuery("SELECT * FROM musicians", null);
            int nameIX = cursor.getColumnIndex("name");
            int ageIX = cursor.getColumnIndex("age");

            while(cursor.moveToNext()){
                System.out.println("Name" + cursor.getString(nameIX));
                System.out.println("Age" + cursor.getInt(ageIX));
            }
            cursor.close();


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}