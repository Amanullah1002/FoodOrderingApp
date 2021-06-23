package com.example.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodorderingapp.Adapters.OrdersAdapter;
import com.example.foodorderingapp.Models.OrdersModel;
import com.example.foodorderingapp.databinding.ActivityOrdersBinding;

import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity {

    ActivityOrdersBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        binding=ActivityOrdersBinding.inflate(getLayoutInflater());
        getSupportActionBar().hide();



        setContentView(binding.getRoot());
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bg));
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);



        DBHelper helper = new DBHelper(this);
        ArrayList<OrdersModel> list=helper.getOrders();

        updateTotal();
//        binding.totalPrice.setText(sum);

//        list.add(new OrdersModel(R.drawable.paneer,"Paneer Tikka Masala","400","11110"));
//        list.add(new OrdersModel(R.drawable.chilli_paneer,"Chilli Paneer","350","10124"));
//        list.add(new OrdersModel(R.drawable.butter_paneer_masala,"Butter Paneer Masala","320","13344"));
//        list.add(new OrdersModel(R.drawable.paneerbiryani2,"Paneer Biryani","320","44545"));
//        list.add(new OrdersModel(R.drawable.noodles,"Chineese Noodles","120","89763"));
//        list.add(new OrdersModel(R.drawable.paneerbiryani,"Vegetarian Biryani","320","63524"));

        OrdersAdapter adapter = new OrdersAdapter(list,this);
        binding.ordersRecyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.ordersRecyclerview.setLayoutManager(layoutManager);

        binding.checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrdersActivity.this,Pop.class);
                startActivity(intent);
            }
        });

    }

    public void updateTotal() {
        DBHelper helper = new DBHelper(this);
        int totalPrice = helper.getSumValue();
        binding.totalPrice.setText(String.valueOf(totalPrice));
    }


//    public void popUP(View view) {
//        Intent intent = new Intent(OrdersActivity.this,Pop.class);
//        startActivity(intent);
//    }
}