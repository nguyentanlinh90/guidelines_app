package com.ntl.guidelinesapp.modules.mvp;

import com.ntl.guidelinesapp.general.model.User;

public class LoginPresenter {
    private ILoginListener iLoginListener;

    public LoginPresenter(ILoginListener iLoginListener) {
        this.iLoginListener = iLoginListener;
    }

    public void receiverData(User user) {
        if (user.isValidUser(user.getEmail(), user.getPassword())) {
            iLoginListener.onSuccess();
        } else {
            iLoginListener.onFail();
        }
    }
}
