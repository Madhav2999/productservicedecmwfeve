package com.scaler.productservicedecmwfeve.services;

import com.scaler.productservicedecmwfeve.models.Category;

import java.util.List;

public interface CategoryService
{
    Category findByName(String name);

    Category findCategoryById(long id);

    List<Category> findAllCategories();
}
