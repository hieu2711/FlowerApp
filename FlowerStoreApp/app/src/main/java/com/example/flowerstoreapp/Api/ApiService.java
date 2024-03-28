package com.example.flowerstoreapp.Api;

import com.example.flowerstoreapp.model.Category;
import com.example.flowerstoreapp.model.Products;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy--MM--dd HH:mm:ss")
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://fl-store-env.eba-migkqjvg.us-west-2.elasticbeanstalk.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("api/v1/flowers")
    Call<List<Products>> getListFlowers();

    @GET("api/v1/categories")
    Call<List<Category>> getListCategory();

    @GET("api/v1/flowers/categories/{id}")
    Call<List<Products>> getListFlowerByIdCate(@Path("id") int idCate);
}
