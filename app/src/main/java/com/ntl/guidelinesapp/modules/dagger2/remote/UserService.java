package com.ntl.guidelinesapp.modules.dagger2.remote;

import com.ntl.guidelinesapp.modules.dagger2.model.UserModel;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface UserService {

    @GET("/users/2")
    Single<UserModel> getUserModel();
}
