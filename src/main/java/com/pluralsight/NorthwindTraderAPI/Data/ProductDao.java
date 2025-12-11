package com.pluralsight.NorthwindTraderAPI.Data;

import com.pluralsight.NorthwindTraderAPI.Models.Product;

import java.sql.SQLException;
import java.util.List;


    public interface ProductDao {
        List<Product> getAll()throws SQLException;
        Product getById(int id)throws SQLException;
        Product insert(Product product)throws SQLException;
        void update(int id, Product product)throws SQLException;
        void delete(int id)throws SQLException;
    }

