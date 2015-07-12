package io.dwak.multiitemrecyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FruitVegAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    @Nullable private FruitVegListener listener;
    private final List<FruitVegItem> list;
    private final Context context;

    public FruitVegAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
        listener = null;
    }

    public FruitVegAdapter(Context context, FruitVegListener listener){
        this.context = context;
        this.listener = listener;
        list = new ArrayList<>();
    }

    public void addFruit(String fruitName){
        list.add(new FruitVegItem<>(fruitName, FruitVegItem.FRUIT));
        notifyItemInserted(list.size());
    }

    public void addVeg(String vegName){
        list.add(new FruitVegItem<>(vegName, FruitVegItem.VEGETABLE));
        notifyItemInserted(list.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType){
            case FruitVegItem.FRUIT:
                return FruitViewHolder.create(context, viewGroup);
            case FruitVegItem.VEGETABLE:
                return VegViewHolder.create(context, viewGroup);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (getItemViewType(position)){
            case FruitVegItem.FRUIT:
                FruitViewHolder.bind((FruitViewHolder) viewHolder,
                                     (String) list.get(position).object,
                                     listener);
                break;
            case FruitVegItem.VEGETABLE:
                VegViewHolder.bind((VegViewHolder) viewHolder,
                                   (String) list.get(position).object,
                                   listener);
                break;

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).viewType;
    }

    public void setListener(FruitVegListener listener) {
        this.listener = listener;
    }

    public interface FruitVegListener {
        void onFruitClicked(String message);
        void onVeggieClicked(String message);
    }
}
