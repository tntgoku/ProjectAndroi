package com.example.myapplication.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.itemslider> {
        private List<Integer> mitem=new ArrayList<>();

        // Constructor Truyen vao List Integer luu tru id IMG trong DRAWble
    public SliderAdapter(List<Integer> mitem) {
        this.mitem = mitem;
    }

    @NonNull
    @Override
    public itemslider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View mview= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_image_adapter,parent,false);

        return new itemslider(mview);
    }

    @Override
    public void onBindViewHolder(@NonNull itemslider holder, int position) {
                holder.Items.setImageResource(mitem.get(position));
    }

    @Override
    public int getItemCount() {
        return mitem.size()!=0?mitem.size():0;
    }

    public class itemslider extends RecyclerView.ViewHolder {
        private ImageView Items;
        public itemslider(@NonNull View itemView) {
            super(itemView);
           Items= itemView.findViewById(R.id.mainimage);

            Items.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    Log.i("So Item", String.valueOf(position));
                }
            });

        }
    }
}
