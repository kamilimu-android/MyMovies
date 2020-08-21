package com.example.myapplication;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/{api_key}")
    Call<List<Movies>> getMyMovies(@Query("api_key")String api_key);
}
