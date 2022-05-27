package com.ntl.guidelinesapp.modules.dagger2.model;

import javax.inject.Inject;

public class Wheels {
    private Tires tires;
    private Rims rims;

    @Inject
    public Wheels(Tires tires, Rims rims) {
        this.tires = tires;
        this.rims = rims;
    }
}
