package com.test.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {


    private Retrofit retrofit;



    public Retrofit getRetrofit(String username, String password){
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor(username, password))
                .build();
        if (retrofit != null){
            retrofit = null;
        }
        retrofit = new Retrofit.Builder()
                .baseUrl("http://test.selliscope.com/api/v1/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}
