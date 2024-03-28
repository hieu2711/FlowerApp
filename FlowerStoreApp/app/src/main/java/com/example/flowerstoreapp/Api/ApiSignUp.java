package com.example.flowerstoreapp.Api;

import com.example.flowerstoreapp.model.Login;
import com.example.flowerstoreapp.model.Register;
import com.example.flowerstoreapp.model.SignUp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiSignUp {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy--MM--dd HH:mm:ss")
            .create();

    ApiSignUp apiService = new Retrofit.Builder()
            .baseUrl("http://fl-store-env.eba-migkqjvg.us-west-2.elasticbeanstalk.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiSignUp.class);

    @POST("api/v1/auth/register")
    Call<String>  SignUp(@Body SignUp signUp);
}
