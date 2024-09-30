package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MySql.ConnectDB;
import com.example.myapplication.Object.Cart;
import com.example.myapplication.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    private List<Cart> mlist;
    public CartAdapter(List<Cart> mlist1){
        mlist=mlist1;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mview= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle,parent,false);


        return new MyViewHolder(mview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Cart sa=mlist.get(position);
        ConnectDB db=new ConnectDB();
        String[] result= db.Query("*","Product where IDP=N'"+sa.getIDP()+"'").split("\t");

        holder.tv1.setText(result[1]);
        holder.tv2.setText(sa.getCost());
        holder.tv3.setText(sa.getQuantity());
        holder.i1.setImageResource(Integer.parseInt(result[7].trim()));

    }


    @Override
    public int getItemCount() {
        return mlist.isEmpty()?0:mlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView i1;
        TextView tv1,tv2,tv3,tv4;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            i1=itemView.findViewById(R.id.imgitem);
            tv2=itemView.findViewById(R.id.costitem);
            tv3=itemView.findViewById(R.id.viewbuyitem);
            tv1=itemView.findViewById(R.id.titleitem);
        }
    }
}
