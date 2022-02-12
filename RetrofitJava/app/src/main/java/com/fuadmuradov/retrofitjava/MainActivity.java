package com.fuadmuradov.retrofitjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<CryptoModel> cryptoModels;
    private String BASE_URL = "https://api.nomics.com/v1/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //https://api.nomics.com/v1/prices?key=e52354a4d89ef7869fb0c33c9034cccb91401c2f
        recyclerView.findViewById(R.id.recycleView);
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

loadData();
    }

    public void loadData(){
       final CryptoApi cryptoApi = retrofit.create(CryptoApi.class);
        Call<List<CryptoModel>> call = cryptoApi.getData();

        call.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {
                 if(response.isSuccessful()){
                     List<CryptoModel> responseList = response.body();
                     cryptoModels = new ArrayList<>(responseList);

                     recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                     recyclerAdapter = new RecyclerAdapter(cryptoModels);
                     recyclerView.setAdapter(recyclerAdapter);

                 }
            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {
            t.printStackTrace();
            }
        });
    }



}