package com.sit.ecom.service;

import com.sit.ecom.entities.CategoryEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private List<CategoryEntity> categories = new ArrayList<>();

    private Long count = 1L;


    @Override
    public List<CategoryEntity> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(CategoryEntity category) {
        category.setCategoryId(count);
        categories.add(category);
        count++;
    }

    @Override
    public String deleteCategory(Long categoryId) {
        CategoryEntity category = categories.stream().filter(c -> c.getCategoryId().equals(categoryId)).findFirst().orElse(null);
        if(category == null){
            return "category id not found";
        }
        categories.remove(category);
        return "Deleted successfully";
    }
}
