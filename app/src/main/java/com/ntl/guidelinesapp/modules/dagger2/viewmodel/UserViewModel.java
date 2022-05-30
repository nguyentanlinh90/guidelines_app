package com.ntl.guidelinesapp.modules.dagger2.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ntl.guidelinesapp.modules.dagger2.model.UserModel;
import com.ntl.guidelinesapp.modules.dagger2.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class UserViewModel extends ViewModel {

    private UserRepository userRepository;
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<UserModel> modelMutableLiveData = new MutableLiveData<>();

    @Inject
    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public MutableLiveData<UserModel> getModelMutableLiveData() {
        loadData();
        return modelMutableLiveData;
    }

    private void loadData() {
        disposable.add(userRepository.modelSingle()
                .subscribeOn(Schedulers.io())
                //use rxAndroid 2
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<UserModel>() {
                    @Override
                    public void onSuccess(UserModel value) {
                        getModelMutableLiveData().setValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                })
        );
    }
}
