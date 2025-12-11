package com.pluralsight.NorthwindTraderAPI.Models;

import org.springframework.beans.factory.annotation.Autowired;

public class Product {
    private int productId;
    private String name;
    private int categoryId;
    private double price;

    @Autowired
    public Product(){}

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //comment

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", price=" + price +
                '}';
    }
}

