package com.scaler.productservicedecmwfeve.controllers;

import com.scaler.productservicedecmwfeve.models.Category;
import com.scaler.productservicedecmwfeve.services.CategoryService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController
{
    @Qualifier("selfCategoryService")
    @Autowired
    CategoryService categoryService;
    @GetMapping
    List<Category>getAllCategories()
    {
        return categoryService.findAllCategories();
    }
    @GetMapping("/{id}")
    Category getSingleCategory(@PathVariable("id") long id)
    {
        return categoryService.findCategoryById(id);
    }
}
