package com.ntl.guidelinesapp.modules.dagger2.model;

import android.util.Log;

import javax.inject.Inject;

public class Car {
    private Engine engine;
    private Wheels wheels;

    @Inject
    public Car(Engine engine, Wheels wheels) {
        this.engine = engine;
        this.wheels = wheels;
    }

    public void drivers(){
        Log.e("CAR", "drivers");
    }
}
