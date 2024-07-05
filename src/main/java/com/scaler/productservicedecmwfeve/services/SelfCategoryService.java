package com.scaler.productservicedecmwfeve.services;

import com.scaler.productservicedecmwfeve.models.Category;
import com.scaler.productservicedecmwfeve.repository.CategoryRespository;
import com.scaler.productservicedecmwfeve.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelfCategoryService implements CategoryService
{
    @Autowired
    CategoryRespository categoryRespository;
    public Category findByName(String name)
    {
        Optional<Category> category  = categoryRespository.findByName(name);
        return category.get();
    }
    public List<Category>findAllCategories()
    {
        return categoryRespository.findAll();
    }
    public Category findCategoryById(long id)
    {
        Optional<Category> category = categoryRespository.findById(id);
        return category.get();
    }
}
