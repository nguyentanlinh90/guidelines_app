package com.ntl.guidelinesapp.modules.dagger2.model;

import javax.inject.Inject;

public class Engine {
    private Block block;
    private Cylinders cylinders;
    private SparkPlugs sparkPlugs;

    @Inject
    public Engine(Block block, Cylinders cylinders, SparkPlugs sparkPlugs) {
        this.block = block;
        this.cylinders = cylinders;
        this.sparkPlugs = sparkPlugs;
    }
}
