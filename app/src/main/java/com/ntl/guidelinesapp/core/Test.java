package com.ntl.guidelinesapp.core;

import android.util.Log;

import com.ntl.guidelinesapp.general.model.General;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Shape s = new Circle();
        s.draw();
    }
}

abstract class Shape {
    abstract void draw();
}

class Circle extends Shape {
    @Override
    void draw() {
        System.out.println("Ve hinh tron");
    }
}

