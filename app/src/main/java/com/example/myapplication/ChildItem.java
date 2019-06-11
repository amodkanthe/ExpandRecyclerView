package com.example.myapplication;

public class ChildItem {
    int index;

    public ChildItem(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public ChildItem setIndex(int index) {
        this.index = index;
        return this;
    }
}
