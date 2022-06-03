package com.ntl.guidelinesapp.kotlin;

import android.util.Log;

public interface ISecond {
    default void add() {
        Log.e("TAG", this.getClass().getSimpleName());
    }

    void add1();
}
