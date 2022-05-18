package com.ntl.guidelinesapp.modules.retrofit.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ntl.guidelinesapp.modules.retrofit.model.ArticleObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {
    //LINK API: https://newsapi.org/v2/everything?q=tesla&from=2022-04-17&sortBy=publishedAt&apiKey=bcb2f4782c934949a08a0282a408bae0
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiService getInstance = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("everything")
    Call<ArticleObject> getArticle(@Query("q") String q,
                                   @Query("from") String from,
                                   @Query("sortBy") String sortBy,
                                   @Query("apiKey") String apiKey);

    //mot vai kieu get khac
    @GET("everything?q=tesla&from=2022-04-17&sortBy=publishedAt&apiKey=bcb2f4782c934949a08a0282a408bae0")
    Call<ArticleObject> getArticle1();

    @GET("everything")
    Call<ArticleObject> getArticle2(@QueryMap Map<String, String> options);

    //Link API: https://apilayer.net/api/users/list
    @GET("api/users/list")
    Call<ArticleObject> getUsers();

    //Link API: https://apilayer.net/api/group/1/users
    @GET("api/group/{id}/users")
    Call<ArticleObject> getUsers(@Path("id") int groupId);

    //Link API: https://apilayer.net/api/group/1/users?sort=desc
    @GET("api/group/{id}/users")
    Call<ArticleObject> getUsers(@Path("id") int groupId, @Query("sort") String desc);

}
