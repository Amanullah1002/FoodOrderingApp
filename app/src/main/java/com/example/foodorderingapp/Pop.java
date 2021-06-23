package com.example.foodorderingapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Pop extends Activity {
        ImageView img2;
        TextView pricetotal;
        Button chkbtn;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_layout);
        DisplayMetrics dm =new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        
        int width=dm.widthPixels;
        int height=dm.heightPixels;

        chkbtn=findViewById(R.id.checkBtn);

//        chkbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendMail();
//            }
//        });

        DBHelper helper = new DBHelper(this);
        int totalPrice = helper.getSumValue();
        pricetotal=findViewById(R.id.pricetotal);
        pricetotal.setText(String.valueOf(totalPrice));
        img2=findViewById(R.id.img2);
        img2.setBackgroundResource(R.drawable.thanking_foodorder_64);
        getWindow().setLayout((int)(width*.8),(int)(height*(0.625)));

//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.gravity= Gravity.CENTER;
//        params x=0;
//        params y=-20;
//
//        getWindow().setAttributes(params);
    }



    public void sendMail(View view) {
        DBHelper helper = new DBHelper(this);
        Cursor res = helper.getAllData();
        StringBuffer buffer = new StringBuffer();
        buffer.append("--------ORDERS---------\n");
        while (res.moveToNext()){
            buffer.append("**********************\n");
            buffer.append("Id: "+ res.getString(0)+"\n");
            buffer.append("Name: "+ res.getString(1)+"\n");
            buffer.append("PhoneNo: "+ res.getString(2)+"\n");
            buffer.append("FoodName: "+ res.getString(3)+"\n");
            buffer.append("Price: "+ res.getString(4)+"\n");
            buffer.append("Quantity: "+ res.getString(5)+"\n");
        }
        String msg=buffer.toString();
        String[] TO = {"amanullah10786@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");

//        emailIntent.setData(Uri.parse("mailto:"));
//        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
//        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EATZONE ORDER");
        emailIntent.putExtra(Intent.EXTRA_TEXT, msg);
        startActivity(emailIntent);
    }
}
