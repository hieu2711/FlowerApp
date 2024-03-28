package com.example.flowerstoreapp.Api;

import com.example.flowerstoreapp.model.Products;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiServiceFlowerFavorites {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy--MM--dd HH:mm:ss")
            .create();

    ApiServiceFlowerFavorites apiServicesFavorites = new Retrofit.Builder()
            .baseUrl("http://fl-store-env.eba-migkqjvg.us-west-2.elasticbeanstalk.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServiceFlowerFavorites.class);

    @GET("api/v1/flowers/favorites")
    Call<List<Products>> getListFlowersFavorites();
}
