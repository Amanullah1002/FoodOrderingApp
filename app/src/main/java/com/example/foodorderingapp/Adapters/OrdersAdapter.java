package com.example.foodorderingapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.DBHelper;
import com.example.foodorderingapp.DetailActivity;
import com.example.foodorderingapp.Models.OrdersModel;
import com.example.foodorderingapp.R;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.viewholder>{


    ArrayList<OrdersModel>list;
    Context context;

    public OrdersAdapter(ArrayList<OrdersModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.order_sample,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
    final OrdersModel model=list.get(position);
    holder.orderImage.setImageResource(model.getOrderImage());
    holder.soldItemName.setText(model.getSoldItemName());
    holder.orderNumber.setText(model.getOrderNumber());
    holder.price.setText(model.getPrice());

//    holder.chkbtn.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent intent=new Intent(context, Pop.class);
//    context.startActivity(intent);
//        }
//    });


    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent(context, DetailActivity.class);
            intent.putExtra("id",Integer.parseInt(model.getOrderNumber()));
            intent.putExtra("type",2);
            context.startActivity(intent);
        }
    });

    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            new AlertDialog.Builder(context)
                    .setTitle("Delete Item")
                    .setIcon(R.drawable.warning)
                    .setMessage("Are You sure you want to delete this item")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DBHelper helper = new DBHelper(context);

                            if (helper.deleteOrder(model.getOrderNumber()) > 0) {
                                Toast.makeText(context,"Deleted",Toast.LENGTH_LONG).show();
                                Intent intent= new Intent(context, OrdersAdapter.class);
                                context.startActivity(intent);
                            } else {
                              Toast.makeText(context,"Error",Toast.LENGTH_LONG).show();
                            }
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            }).show();
            return false;
        }
    });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        ImageView orderImage;
        TextView  soldItemName,orderNumber,price;
        Button chkbtn;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            chkbtn=itemView.findViewById(R.id.checkBtn);
            orderImage=itemView.findViewById(R.id.orderImage);
            soldItemName=itemView.findViewById(R.id.soldItemName);
            orderNumber=itemView.findViewById(R.id.orderNumber);
            price=itemView.findViewById(R.id.price);


        }



    }
}
