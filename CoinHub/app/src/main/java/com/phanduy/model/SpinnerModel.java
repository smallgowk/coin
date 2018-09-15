package com.phanduy.model;

/**
 * Created by duyuno on 11/20/16.
 */
public class SpinnerModel {
    private int value;
    private String name;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SpinnerModel(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
