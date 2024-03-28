package com.example.flowerstoreapp.Api;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIForgetPassword {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://fl-store-env.eba-migkqjvg.us-west-2.elasticbeanstalk.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();

    APIForgetPassword apiService = retrofit.create(APIForgetPassword.class);


    @POST("api/v1/accounts/forgot-password")
    Call<String> forgotPass(@Query("email") String email);

    @GET("api/v1/accounts/reset-password")
    Call<String> resetPass(@Query("token") String token);


}
