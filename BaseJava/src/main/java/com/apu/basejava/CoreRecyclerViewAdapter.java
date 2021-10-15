package com.apu.basejava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apu.basejava.Helper.AnimationHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class CoreRecyclerViewAdapter extends RecyclerView.Adapter<CoreRecyclerViewAdapter.CoreViewHolder> {

    public int layout_id;
    protected List<?> dataList = new ArrayList<>();
    protected CoreActivity Context;
    protected int ScrollPosition = -1;
    protected int animeRAW;


    public CoreRecyclerViewAdapter(CoreActivity context) {
        this.Context = context;
    }

    @NonNull
    @Override
    public CoreViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout_id, viewGroup, false);
        return new CoreViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CoreViewHolder viewHolder, int position) {
        onViewHolderBind(viewHolder, position, dataList.get(position));
        AnimationHelper.setGeneralItemAnimation(Context, viewHolder.itemView, position, ScrollPosition, animeRAW);
        ScrollPosition++;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public abstract void onViewHolderBind(CoreViewHolder viewHolder, int position, Object data);

    public class CoreViewHolder extends RecyclerView.ViewHolder {
        public CoreViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}