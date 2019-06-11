package com.example.myapplication;

import java.util.List;

public class ParentItem {
    int index;
    List<ChildItem> childItems;
    boolean isExpanded = true;

    public ParentItem(int index, List<ChildItem> childItems) {
        this.index = index;
        this.childItems = childItems;
    }

    public int getIndex() {
        return index;
    }

    public ParentItem setIndex(int index) {
        this.index = index;
        return this;
    }
}
