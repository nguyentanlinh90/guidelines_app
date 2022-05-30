package com.ntl.guidelinesapp.modules.dagger2.di.components;

import com.ntl.guidelinesapp.modules.dagger2.Dagger2Activity;
import com.ntl.guidelinesapp.modules.dagger2.di.modules.ContextModule;
import com.ntl.guidelinesapp.modules.dagger2.di.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, ContextModule.class})
public interface AppComponent {
    void inject(Dagger2Activity dagger2Activity);
}
