package com.example.flowerstoreapp.Api;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIResetPassWord {
    APIResetPassWord apiService = new Retrofit.Builder()
            .baseUrl("http://fl-store-env.eba-migkqjvg.us-west-2.elasticbeanstalk.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIResetPassWord.class);

    @FormUrlEncoded
    @POST("api/v1/accounts/reset-password")
    Call<String> resetPass(
            @Field("token") String token,
            @Field("password") String newPass
    );
}
