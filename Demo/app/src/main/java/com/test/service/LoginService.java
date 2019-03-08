package com.test.service;

import com.test.model.Login;
import com.test.model.ResObj;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {


    @POST("login")
    Call<ResObj> login(@Body Login login);


}
