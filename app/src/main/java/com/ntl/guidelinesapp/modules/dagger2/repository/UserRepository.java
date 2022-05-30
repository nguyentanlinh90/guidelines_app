package com.ntl.guidelinesapp.modules.dagger2.repository;

import com.ntl.guidelinesapp.modules.dagger2.model.UserModel;
import com.ntl.guidelinesapp.modules.dagger2.remote.UserService;

import javax.inject.Inject;

import io.reactivex.Single;

public class UserRepository {

    private UserService userService;

    @Inject
    public UserRepository(UserService userService) {
        this.userService = userService;
    }

    public Single<UserModel> modelSingle() {
        return userService.getUserModel();
    }
}
