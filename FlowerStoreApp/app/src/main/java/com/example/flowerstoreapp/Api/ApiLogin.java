package com.example.flowerstoreapp.Api;

import com.example.flowerstoreapp.model.Login;
import com.example.flowerstoreapp.model.LoginResponse;
import com.example.flowerstoreapp.model.Products;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiLogin {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy--MM--dd HH:mm:ss")
            .create();

    ApiLogin apiService = new Retrofit.Builder()
            .baseUrl("http://fl-store-env.eba-migkqjvg.us-west-2.elasticbeanstalk.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiLogin.class);

    @POST("api/v1/auth/login")
    Call<LoginResponse>  login(@Body Login login);
}

