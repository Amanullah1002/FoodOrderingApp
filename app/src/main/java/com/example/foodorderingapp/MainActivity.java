package com.example.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodorderingapp.Adapters.MainAdapter;
import com.example.foodorderingapp.Models.MainModel;
import com.example.foodorderingapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bg));
        actionBar.setTitle("EATZONE");
        getSupportActionBar().setIcon(R.drawable.applogo_2);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


//            public void display(int number) {
//                TextView quantityText = (TextView) findViewById(R.id.quantity);
//                quantityText.setText("" + quantity);
//            }

        ArrayList<MainModel> list= new ArrayList<>();



        list.add(new MainModel(R.drawable.paneer, "Paneer Tikka",  "250",  "Tandoori grilled paneer tikka."));
        list.add(new MainModel(R.drawable.chilli_paneer, "Chilli Paneer",  "240",  "Extra spicy chili paneer."));
        list.add(new MainModel(R.drawable.butter_paneer_masala, "Butter Paneer Masala",  "130",  "A rich and creamy dish of paneer."));
       list.add(new MainModel(R.drawable.paneer_biryani, "Paneer Biryani",  "400",  "Mildly spicy paneer biryani."));
        list.add(new MainModel(R.drawable.veg_burger, "Veg Burger",  "50",  "A delicious veggie burger."));
        list.add(new MainModel(R.drawable.margherita_pizza, "Margherita Pizza",  "400",  "A large extra cheesy Margherita pizza."));
        list.add(new MainModel(R.drawable.red_sauce_pasta, "Red Sauce Pasta",  "180",  "A spicy pasta dish Margherita pizza."));

        list.add(new MainModel(R.drawable.white_sauce_pasta, "White Sauce Pasta",  "200",  "A delicious pasta made of cream and cheese."));
        list.add(new MainModel(R.drawable.veg_extravaganza, "Extravaganza Pizza",  "500",  "A spicy large pizza topped with veggies"));
        list.add(new MainModel(R.drawable.french_fries, "French Fries",  "50",  "A dish of fried cuboidal pieces of potato."));
        list.add(new MainModel(R.drawable.cheese_balls, "Cheese Balls",  "120",  "A delicious starter with cheese stuffing."));
        list.add(new MainModel(R.drawable.samosa, "Samosa",  "15",  "A fried dish with potato stuffing."));
        list.add(new MainModel(R.drawable.poha, "Poha",  "20",  "A very popular dish from Indore city."));
        list.add(new MainModel(R.drawable.kachori, "Kachori",  "20",  "A fried dish with moong dal stuffing."));

        list.add(new MainModel(R.drawable.aloo_vada, "Alood Vada",  "15",  "A fried dish with potato stuffing"));
        list.add(new MainModel(R.drawable.club_sandwich, "Club Sandwich",  "100",  "A baked sandwich with cottage cheese stuffing"));
        list.add(new MainModel(R.drawable.vegetable_maggi, "Vegetable Maggi",  "50",  "Extra masala vegetable maggi."));
        list.add(new MainModel(R.drawable.chocolate_pastry, "Chocolate Pastry",  "60",  "A chocolate-flavored cake-type dish."));
        list.add(new MainModel(R.drawable.chocolate_brownie, "Chocolate Brownie",  "150",  "A brownie with chocolate on top."));
        list.add(new MainModel(R.drawable.masala_chai, "Masala Chai",  "20",  "A hot tea beverage in milk commonly served in India. "));

        list.add(new MainModel(R.drawable.hot_coffee, "Hot Coffee",  "70",  "A hot-served coffee beverage."));
        list.add(new MainModel(R.drawable.cold_coffee, "Cold Coffee",  "300",  "A cold-served coffee beverage.. "));
        list.add(new MainModel(R.drawable.vanilla_ice_cream, "Vanilla Ice Cream",  "140",  "Real milk vanilla-flavored ice cream. "));
        list.add(new MainModel(R.drawable.chocolate_ice_cream, "Chocolate Ice Cream",  "180",  "Real milk chocolate-flavored ice cream."));

        list.add(new MainModel(R.drawable.mango_ice_cream, "Mango Ice Cream",  "180",  "Real milk mango-flavored ice cream. "));


        MainAdapter adapter =new MainAdapter(list,this);
        binding.recyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.orders:
                startActivity(new Intent(MainActivity.this,OrdersActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}