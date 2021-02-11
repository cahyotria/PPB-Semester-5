package com.example.daftarkotaapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("restapiandroid.php")
    Call<List<GetData>> getPost();
}
