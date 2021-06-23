package com.example.foodorderingapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodorderingapp.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bg));
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        actionBar.setTitle("EATZONE");



        final DBHelper helper = new DBHelper(this);

        if (getIntent().getIntExtra("type", 0) == 1) {
            int image = getIntent().getIntExtra("image", 0);
            int price = Integer.parseInt(getIntent().getStringExtra("price"));
            String name = getIntent().getStringExtra("name");

            String description = getIntent().getStringExtra("desc");

            binding.detailimage.setImageResource(image);
            binding.priceLbl.setText(String.format("%d", price));
            binding.foodName.setText(name);
            binding.detailDescription.setText(description);


            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isInserted = helper.insertOrder(
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            price,
                            image,
                            quantity=getQuanity(),
//                            Integer.parseInt(quantity),
                            description,
                            name

//                        "PRICE INT,"+
//                                "IMAGE INT,"+
//                                "QUANTITY INT,"+
//                                "DESCRIPTION TEXT,"+
//                                "FOODNAME TEXT)"
                    );

                    if (isInserted) {
                        Toast.makeText(DetailActivity.this, "Data inserted successfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_LONG).show();
                    }
                }
            });


        } else {
            int id = getIntent().getIntExtra("id", 0);
            Cursor cursor = helper.getOrderById(id);
            int image = cursor.getInt(4);
            binding.detailimage.setImageResource(image);
            binding.priceLbl.setText(String.format("%d", cursor.getInt(3)));
            binding.foodName.setText(cursor.getString(7));
            binding.quantity.setText(String.format("%d", cursor.getInt(5)));
            binding.detailDescription.setText(cursor.getString(6));
            Toast.makeText(DetailActivity.this, cursor.getString(1), Toast.LENGTH_SHORT).show();

            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));
            binding.insertBtn.setText("Update Now");
            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isUpdated = helper.updateOrder(
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            Integer.parseInt(binding.priceLbl.getText().toString()),
                            image,
                            quantity=getQuanity(),
                            binding.detailDescription.getText().toString(),
                            binding.foodName.getText().toString(),
                            id);

                    if (isUpdated) {

                        Toast.makeText(DetailActivity.this, "Updated", Toast.LENGTH_SHORT).show();

//                            Intent intent= new Intent(DetailActivity.this, OrdersActivity.class);
//                            startActivity(intent);

//                        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
////                            @Override
////                            public void handleOnBackPressed() {
////                                // Handle the back button event
////
////                            }
////                        };
////                        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

                    } else {
                        Toast.makeText(DetailActivity.this, "Fail to Updated", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }


    int quantity = 1;

    public void increment(View view) {
        quantity = quantity + 1;
        TextView quantityText = (TextView) findViewById(R.id.quantity);
        quantityText.setText("" + quantity);

//        binding.quantity.setText(quantity);

    }

    public void decrement(View view) {
        if (quantity > 0) {
            quantity = quantity - 1;
            TextView quantityText = (TextView) findViewById(R.id.quantity);
            quantityText.setText("" + quantity);

//            binding.quantity.setText(quantity);
        }
    }


        public void setQuantity(int q1) {
            this.quantity = q1;
        }

        public int getQuanity() {
            return quantity;
        }


}

