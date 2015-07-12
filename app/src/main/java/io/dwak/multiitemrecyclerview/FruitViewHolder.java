package io.dwak.multiitemrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FruitViewHolder extends RecyclerView.ViewHolder{

    public TextView name;

    public FruitViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name);
    }

    public static FruitViewHolder create(Context context, ViewGroup parent){
        return new FruitViewHolder(LayoutInflater.from(context).inflate(R.layout.item_fruit_veg, parent, false));
    }

    public static void bind(FruitViewHolder holder, final String name, final FruitVegAdapter.FruitVegListener listener){
        holder.itemView.setBackgroundResource(R.color.fruit_bg);
        holder.name.setText(name);
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onFruitClicked(name);
                }
            }
        });
    }

}
