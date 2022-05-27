package com.ntl.guidelinesapp.modules.dagger2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.dagger2.components.CarComponent;
import com.ntl.guidelinesapp.modules.dagger2.components.DaggerCarComponent;
import com.ntl.guidelinesapp.modules.dagger2.model.Car;

public class Dagger2Activity extends AppCompatActivity {

    private Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);
        AppUtils.setTitleBar(this, Dagger2Activity.class);

        CarComponent carComponent = DaggerCarComponent.create();
        car = carComponent.getCar();
        car.drivers();
    }
}