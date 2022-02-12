package com.fuadmuradov.retrofitjava;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoApi {

    // GET, POST, UPDATE, DELETE

    @GET("prices?key=e52354a4d89ef7869fb0c33c9034cccb91401c2f")
    Call<List<CryptoModel>> getData();


}
