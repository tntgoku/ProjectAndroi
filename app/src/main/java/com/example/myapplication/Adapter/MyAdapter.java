package com.example.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Object.Products;
import com.example.myapplication.R;
import com.example.myapplication.FragmentITem.Activityinforitem;

import java.util.List;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Products> mlist;
    Context mcontext;

    public MyAdapter(List<Products> mlist1,Context mcontext1){
        mlist=mlist1;
        mcontext=mcontext1;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Products sa=mlist.get(position);
            holder.img.setImageResource(sa.getImg());
            holder.tv.setText(sa.getNameP());
            holder.tv1.setText(String.valueOf(sa.getCost()));
            holder.tv2.setText(String.valueOf(sa.getQuantity()));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent((Activity)holder.itemView.getContext(), Activityinforitem.class);
                        i.putExtra("itemObject",sa);
                    holder.itemView.getContext().startActivity(i);
                }
            });
    }

    @Override
    public int getItemCount() {
        return mlist.isEmpty() ? 0 : mlist.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
       private ImageView img;
       private TextView tv,tv1,tv2;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.imgitem);
            tv=itemView.findViewById(R.id.titleitem);
            tv1=itemView.findViewById(R.id.costitem);
            tv2=itemView.findViewById(R.id.viewbuyitem);

        }
    }
}
