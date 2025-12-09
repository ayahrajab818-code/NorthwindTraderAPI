package com.pluralsight.NorthwindTraderAPI;

import com.pluralsight.NorthwindTraderAPI.Models.Product;

import java.util.List;


    public interface ProductDao {
        List<Product> getAll();
        Product getById(int id);
    }

