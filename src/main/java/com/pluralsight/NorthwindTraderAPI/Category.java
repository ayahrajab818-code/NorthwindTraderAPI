package com.pluralsight.NorthwindTraderAPI;

import java.util.List;

public interface Category {
    List<Category> getAll();
    Category getById(int id);
}
