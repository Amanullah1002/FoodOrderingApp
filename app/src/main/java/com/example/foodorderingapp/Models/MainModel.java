package com.example.foodorderingapp.Models;

public class MainModel {

        int image;
        String name,orderPrice,description;

        public MainModel(int image, String name, String price, String description) {
            this.image = image;
            this.name = name;
            this.orderPrice = price;
            this.description = description;
        }

        public int getImage() {

            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return orderPrice;
        }

        public void setPrice(String price) {
            this.orderPrice = price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }



    }


