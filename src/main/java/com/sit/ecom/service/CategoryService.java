package com.sit.ecom.service;

import com.sit.ecom.entities.CategoryEntity;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> getAllCategories();
    void createCategory(CategoryEntity category);
    String deleteCategory(Long categoryId);
}
