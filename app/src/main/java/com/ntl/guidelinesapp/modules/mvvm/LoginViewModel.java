package com.ntl.guidelinesapp.modules.mvvm;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.ntl.guidelinesapp.BR;
import com.ntl.guidelinesapp.general.model.User;

import java.util.ArrayList;
import java.util.List;

public class LoginViewModel extends BaseObservable {
    private String email;
    private String password;

    public ObservableField<String> messageLogin = new ObservableField<>();
    public ObservableField<Boolean> isShowMessage = new ObservableField<>();
    public ObservableField<Boolean> isLogin = new ObservableField<>();

    private MutableLiveData<List<User>> mutableLiveData;
    private List<User> mList;

    public LoginViewModel() {
        mutableLiveData = new MutableLiveData<>();
        mList = new ArrayList<>();
//        mutableLiveData.setValue(mList);

    }

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

            //add user to list
            mList.add(0, user);
            mutableLiveData.setValue(mList);
        } else {
            messageLogin.set("Login Fail");
            isLogin.set(false);
        }
    }

    public MutableLiveData<List<User>> getMutableLiveData() {
        return mutableLiveData;
    }
}
