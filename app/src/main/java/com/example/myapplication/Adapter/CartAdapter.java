package com.example.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.FragmentITem.Fragment_1;
import com.example.myapplication.MySql.ConnectDB;
import com.example.myapplication.Object.Cart;
import com.example.myapplication.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    private List<Cart> mlist;
    private   boolean isVisibility=false;
    public   boolean isVisibility() {
        return isVisibility;
    }

    public void setVisibility(boolean visibility) {
        isVisibility = visibility;
    }
    public CartAdapter(List<Cart> mlist1){
        mlist=mlist1;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=null;
        switch (viewType) {
            case 0:
                // Nếu danh sách có phần tử, dùng layout item_recycle
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle, parent, false);
                break;
            case 1:
                // Nếu danh sách trống, dùng layout itemnothing
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemnothing, parent, false);
                break;
            default:
                throw new IllegalArgumentException("Không xác định được viewType: " + viewType);
        }
        return new MyViewHolder(view);
    }
    // Method to update visibility of i3
    public void setI3Visibility(boolean visibility) {
        isVisibility = visibility;
        notifyDataSetChanged(); // This will refresh the RecyclerView to apply changes
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(getItemViewType(position)==0){
            Cart sa=mlist.get(position);
            ConnectDB db=new ConnectDB();
            db.conn= db.getConn();
                    String sa1= db.Query("*","Product where IDP=N'"+sa.getIDP()+"'");

                    Log.i("TAG",sa1);
            String[] result=sa1.split("\t");
                if(Integer.parseInt(result[3])!=0){
                    holder.i2.setVisibility(View.INVISIBLE);
                }else{
                    holder.i2.setVisibility(View.VISIBLE);
                }
            holder.tv1.setText(result[1]);
            holder.tv2.setText(String.valueOf(sa.getTotal()));
            holder.tv3.setText(sa.getQuantity());
            holder.tv4.setText(result[3]);
            holder.i1.setImageResource(Integer.parseInt(result[7].trim()));
            holder.i3.setImageResource(R.drawable.google);
                holder.i3.setVisibility(View.VISIBLE);
            // Set visibility for i3
            if (isVisibility) {
                holder.i3.setVisibility(View.VISIBLE);
                Log.i("TAG","Dang bat");
            } else {
                holder.i3.setVisibility(View.INVISIBLE);
                Log.i("TAG","Dang Tat");
            }


            holder.tv5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("TAG","Position+ "+String.valueOf(position));
                    int j=sa.getIntQuantity()-1;
                    sa.setQuantity(j);
                    Log.e("TAG","ID Item: "+result[0]+"Quantity Item: "+String.valueOf(sa.getIntQuantity()));
                    holder.tv3.setText(String.valueOf(j));

                    //remove object
                    if(j==0){
                        mlist.remove(position);
                        notifyDataSetChanged();
                    }
                    Fragment_1.updatetotal();
                }
            });

            holder.tv6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("TAG","Position:  "+String.valueOf(position));
                    int j=sa.getIntQuantity()+1;
                    sa.setQuantity(j);
                    Log.e("TAG","ID Item: "+result[0]+"Quantity Item: "+String.valueOf(sa.getIntQuantity())+
                            "\n"+"Quantity da tang len :"+String.valueOf(sa.getIntQuantity()));
                    holder.tv3.setText(String.valueOf(j));

                }
            });
        }

    }



    public void toggleI3Visibility() {
        isVisibility = !isVisibility;
    }
    @Override
    public int getItemCount() {
        return mlist.isEmpty()?0:mlist.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mlist.size() > 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView i1,i2,i3;
        TextView tv1,tv2,tv3,tv4,tv5,tv6;

        View sa;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            if (CartAdapter.this.getItemViewType(getLayoutPosition())==0){
            sa=itemView;
            i1=itemView.findViewById(R.id.imgitem);
            i2=itemView.findViewById(R.id.sold);
            i3=itemView.findViewById(R.id.imageView3test);
            tv1=itemView.findViewById(R.id.titleitem);
            tv2=itemView.findViewById(R.id.costitemcount);
            tv3=itemView.findViewById(R.id.viewbuyitem);
            tv4=itemView.findViewById(R.id.quantity);
            tv5=itemView.findViewById(R.id.minus);
            tv6=itemView.findViewById(R.id.plus);
            }

        }


    }
}
