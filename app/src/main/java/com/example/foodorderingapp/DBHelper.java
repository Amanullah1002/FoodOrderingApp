package com.example.foodorderingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.foodorderingapp.Models.OrdersModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBNAME="mydatabase.db";

    final static int DBVERSION=1;
    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE ORDERS"+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+ "NAME TEXT,"+
                        "PHONE TEXT,"+
                        "PRICE INT,"+
                        "IMAGE INT,"+
                        "QUANTITY INT,"+
                        "DESCRIPTION TEXT,"+
                        "FOODNAME TEXT)"
        );
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ORDERS");
    onCreate(sqLiteDatabase);
    }

    public boolean insertOrder(String name,String phone,int price,int image,int quantity,String desc,String foodname){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("NAME",name);
        values.put("PHONE",phone);
        values.put("PRICE",price);
        values.put("IMAGE",image);
        values.put("QUANTITY",quantity);
        values.put("DESCRIPTION",desc);
        values.put("FOODNAME",foodname);


        long id=database.insert("orders",null,values);
        if(id<=0)
        {
            return false;
        }
        else{
            return true;
        }

    }

    public ArrayList<OrdersModel> getOrders(){
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id,foodname,image,price,quantity from orders",null);
//        if(cursor.moveToFirst()){
        /*
        id-0
        foodname-1
        image=2
        price -3
        qunatity-4
         */
            while (cursor.moveToNext())
            {
                OrdersModel model= new OrdersModel();
                model.setOrderNumber(cursor.getInt(0)+"");
                model.setSoldItemName(cursor.getString(1));
                model.setOrderImage(cursor.getInt(2));
                model.setPrice(cursor.getInt(3)+"");
                model.setQuantity(cursor.getInt(4)+" ");
                orders.add(model);

//            }
        }

        cursor.close();
        database.close();
        return orders;
    }

    public ArrayList<OrdersModel> getAll(){
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id,name,phone,foodname,price,quantity from orders",null);
//        if(cursor.moveToFirst()){
        /*
        id-0
        name-1
        phone-2
        foodname-3
        image=4
        price -5
        qunatity-6
         */
        String[] list1;
        while (cursor.moveToNext())
        {
            OrdersModel model= new OrdersModel();
            model.setOrderNumber(cursor.getInt(0)+"");
            model.setOrderPhone(cursor.getString(2)+"");
            model.setSoldItemName(cursor.getString(3));
            model.setName(cursor.getString(1));
            model.setPrice(cursor.getInt(3)+"");
            model.setQuantity(cursor.getInt(4)+" ");
            orders.add(model);
//            }
        }

        cursor.close();
        database.close();
        return orders;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        /*
        id-0
        name-1
        phone-2
        foodname-3
        price-4
        quantity-5
        */

        Cursor res = db.rawQuery("Select id,name,phone,foodname,price,quantity from orders",null);
        return res;
    }

    public Cursor getOrderById(int id){

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from orders where id ="+id,null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
//        cursor.close();
//        database.close();
        return cursor;

    }

public int getSumValue(){
    int sum=0;
    SQLiteDatabase database = this.getWritableDatabase();
    String sumQuery=String.format("SELECT SUM(quantity*price) as Total FROM orders");
    Cursor cursor=database.rawQuery(sumQuery,null);
    if(cursor.moveToFirst())
        sum= cursor.getInt(cursor.getColumnIndex("Total"));
    return sum;
}

    public boolean updateOrder(String name,String phone,int price,int image,int quantity,String desc,String foodname,int id){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        /*
        name 1
        phone 2
        price 3
        image 4
        quantity 5
        description 6
        food name 7
         */
        values.put("NAME",name);
        values.put("PHONE",phone);
        values.put("PRICE",price);
        values.put("IMAGE",image);
        values.put("QUANTITY",quantity);
        values.put("DESCRIPTION",desc);
        values.put("FOODNAME",foodname);


//        long row=database.update("orders",values,"id="+id);
        long row =database.update("orders",values,"id="+id,null);
        if(row<=0)
        {
            return false;
        }
        else{
            return true;
        }
    }

    public int deleteOrder(String id)
    {
        SQLiteDatabase database= this.getWritableDatabase();
        database.execSQL("delete from orders where id ="+id);
        return 1;
    }

}
