package com.ntl.guidelinesapp.modules.dagger2.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ntl.guidelinesapp.modules.dagger2.di.ViewModelKey;
import com.ntl.guidelinesapp.modules.dagger2.viewmodel.UserViewModel;
import com.ntl.guidelinesapp.modules.dagger2.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel.class)
    abstract ViewModel bindViewModel(UserViewModel userViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindFactory(ViewModelFactory viewModelFactory);
}
