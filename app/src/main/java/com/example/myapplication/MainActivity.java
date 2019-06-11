package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvRecyclerView;
    SampleAdapter sampleAdapter;
    List<Object> objects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvRecyclerView = (RecyclerView) findViewById(R.id.rvRecyclerView);
        rvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        generateData();
        sampleAdapter = new SampleAdapter(objects, new SampleAdapter.ExpandCollpaseListener() {
            @Override
            public void onExpandCollpase(int index, ParentItem parentItem) {
                parentItem.isExpanded = !parentItem.isExpanded;
                sampleAdapter.notifyItemChanged(index);
                if (parentItem.isExpanded) {
                    objects.addAll(index + 1, parentItem.childItems);
                    sampleAdapter.notifyItemRangeInserted(index + 1, parentItem.childItems.size());

                } else {
                    objects.removeAll(parentItem.childItems);
                    sampleAdapter.notifyItemRangeRemoved(index + 1, parentItem.childItems.size());

                }
            }
        });
        rvRecyclerView.setAdapter(sampleAdapter);
    }

    public void generateData() {
        List<ParentItem> parentItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<ChildItem> childItems = new ArrayList<>();
            for (int j = 0; j < 15; j++) {
                childItems.add(new ChildItem(j));
            }
            ParentItem parentItem = new ParentItem(i, childItems);
            parentItems.add(parentItem);
        }
        for (ParentItem parentItem : parentItems) {
            objects.add(parentItem);
            objects.addAll(parentItem.childItems);
        }
    }
}
