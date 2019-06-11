package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SampleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Object> objectList;
    ExpandCollpaseListener expandCollpaseListener;


    public static final int TYPE_PARENT = 0;
    public static final int TYPE_CHILD = 1;


    @Override
    public int getItemViewType(int position) {
        Object object = objectList.get(position);
        if(object instanceof  ParentItem){
            return TYPE_PARENT;
        }else {
            return TYPE_CHILD;
        }
    }

    public SampleAdapter(List<Object> objectList, ExpandCollpaseListener expandCollpaseListener) {
        this.objectList = objectList;
        this.expandCollpaseListener = expandCollpaseListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        switch (getItemViewType(i)) {
            case TYPE_PARENT:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.parent_row, viewGroup, false);
                return new ParentViewHolder(view);
            case TYPE_CHILD:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.child_row, viewGroup, false);
                return new ChildViewHolder(view);
            default:
                throw new IllegalArgumentException("Invalid viewType");

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Object object = objectList.get(i);
        if(getItemViewType(i) == TYPE_PARENT){
            ParentViewHolder parentViewHolder = (ParentViewHolder)viewHolder;
            ParentItem parentItem = (ParentItem)object;
            if(parentItem.isExpanded) {
                parentViewHolder.tvTitle.setText("Parent (Click here to Collapse):" + parentItem.getIndex());
            }else {
                parentViewHolder.tvTitle.setText("Parent (Click here to expand):" + parentItem.getIndex());
            }
        }else {
            ChildViewHolder childViewHolder = (ChildViewHolder) viewHolder;
            ChildItem childItem = (ChildItem) object;
            childViewHolder.tvTitle.setText("Child:"+childItem.getIndex());
        }
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    public class ParentViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        public ParentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.tvTitle);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(objectList.get(getAdapterPosition()) instanceof ParentItem) {
                        expandCollpaseListener.onExpandCollpase(getAdapterPosition(),(ParentItem)objectList.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.tvTitle);

        }
    }

    public interface ExpandCollpaseListener{
        public void onExpandCollpase(int index,ParentItem parentItem);
    }
}
