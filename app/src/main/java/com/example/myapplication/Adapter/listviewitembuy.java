package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Object.Products;
import com.example.myapplication.R;

import java.util.List;

public class listviewitembuy extends  RecyclerView.Adapter<listviewitembuy.ItemviewHolder> {
    private  List<Products> mlist1;
    public listviewitembuy(List<Products> mlist){
        this.mlist1=mlist;
    }
    @NonNull
    @Override
    public ItemviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mview= LayoutInflater.from(parent.getContext()).inflate(R.layout.itembuys,parent,false);

        return new ItemviewHolder(mview);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemviewHolder holder, int position) {
            Products mlist12=mlist1.get(position);

            holder.img.setImageResource(mlist12.getImg());
            holder.tv.setText(mlist12.getNameP());
            holder.tv2.setText(String.valueOf(mlist12.getQuantity()));

    }

    @Override
    public int getItemCount() {
        return mlist1.isEmpty()?0:mlist1.size();
    }

    public class ItemviewHolder extends RecyclerView.ViewHolder{
            ImageView img;
            TextView tv,tv1,tv2;
        public ItemviewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.imageViewbuys);
            tv=itemView.findViewById(R.id.textViewtitle);
            tv1=itemView.findViewById(R.id.textViewselect);
            tv2=itemView.findViewById(R.id.textViewquantity);
        }
    }
}
