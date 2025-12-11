package com.pluralsight.NorthwindTraderAPI.Data;

import java.util.List;

public interface Category {
    List<Category> getAll();
    Category getById(int id);
}
