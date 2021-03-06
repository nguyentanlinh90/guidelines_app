package com.ntl.guidelinesapp.modules.retrofit.api;

import static com.ntl.guidelinesapp.modules.retrofit.utils.Constants.KEY_PASSWORD;
import static com.ntl.guidelinesapp.modules.retrofit.utils.Constants.KEY_USERNAME;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ntl.guidelinesapp.modules.retrofit.model.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService2 {
    //LINK API: https://accountservermanagement.herokuapp.com/api/accounts
    String DOMAIN = "https://accountservermanagement.herokuapp.com/api/";

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiService2 getInstance = new Retrofit.Builder()
            .baseUrl(DOMAIN)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService2.class);

    @Multipart
    @POST("accounts")
    Call<User> createAccount(@Part(KEY_USERNAME) RequestBody username,
                             @Part(KEY_PASSWORD) RequestBody password,
                             @Part MultipartBody.Part avt);


}
