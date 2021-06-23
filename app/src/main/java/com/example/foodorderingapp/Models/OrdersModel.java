package com.example.foodorderingapp.Models;

public class OrdersModel {
    int orderImage;
    String soldItemName,price,orderNumber,totalprice,name,phone;
    String quantity;

    public OrdersModel() {
        this.orderImage = orderImage;
        this.soldItemName = soldItemName;
        this.price = price;
        this.orderNumber = orderNumber;
        this.quantity=quantity;
    }
//    public OrdersModel(int orderImage, String soldItemName, String price, String orderNumber) {
//        this.orderImage = orderImage;
//        this.soldItemName = soldItemName;
//        this.price = price;
//        this.orderNumber = orderNumber;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setQuantity(String quantity) {
    this.quantity = quantity;
}

    public String getQuanity() {
        return quantity;
    }

    public String getOrderPhone() {
        return phone;
    }

    public void setOrderPhone(String phone) {
        this.phone = phone;
    }

    public int getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(int orderImage) {
        this.orderImage = orderImage;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getSoldItemName() {
        return soldItemName;
    }

    public void setSoldItemName(String soldItemName) {
        this.soldItemName = soldItemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }



}
