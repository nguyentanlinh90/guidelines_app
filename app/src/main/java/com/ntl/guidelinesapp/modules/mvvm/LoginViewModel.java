package com.ntl.guidelinesapp.modules.mvvm;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.ntl.guidelinesapp.BR;
import com.ntl.guidelinesapp.general.model.User;

public class LoginViewModel extends BaseObservable {
    private String email;
    private String password;

    public ObservableField<String> messageLogin = new ObservableField<>();
    public ObservableField<Boolean> isShowMessage = new ObservableField<>();
    public ObservableField<Boolean> isLogin = new ObservableField<>();

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public void clickLogin() {
        User user = new User(getEmail(), getPassword());
        isShowMessage.set(true);
        if (user.isValidUser(user.getEmail(), user.getPassword())) {
            messageLogin.set("Login Success");
            isLogin.set(true);
        } else {
            messageLogin.set("Login Fail");
            isLogin.set(false);
        }
    }
}
