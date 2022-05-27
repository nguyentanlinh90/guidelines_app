package com.ntl.guidelinesapp.modules.dagger2.components;

import com.ntl.guidelinesapp.modules.dagger2.model.Car;

import dagger.Component;

@Component
public interface CarComponent {
    Car getCar();
}
