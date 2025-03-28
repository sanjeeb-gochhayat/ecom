package com.sit.ecom.service;

import com.sit.ecom.entities.CategoryEntity;
import com.sit.ecom.exceptions.APIException;
import com.sit.ecom.exceptions.ResourceNotFoundException;
import com.sit.ecom.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    //private List<CategoryEntity> categories = new ArrayList<>();

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<CategoryEntity> getAllCategories() {
        List<CategoryEntity> categories = categoryRepo.findAll();
        if(categories.isEmpty()){
            throw new APIException("No category created till now.");
        }
        return categories;
    }

    @Override
    public void createCategory(CategoryEntity category) {
        CategoryEntity savedCategory = categoryRepo.findByCategoryName(category.getCategoryName());
        if(savedCategory != null){
            throw new APIException("Category with the name " + category.getCategoryName() + " already exist !!!");
        }
        categoryRepo.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
//        List<CategoryEntity> categories = categoryRepo.findAll();
//        CategoryEntity category = categories.stream().filter(c -> c.getCategoryId().equals(categoryId)).findFirst().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));
        Optional<CategoryEntity> selectedCategoryOptional = categoryRepo.findById(categoryId);
        CategoryEntity selectedCategory = selectedCategoryOptional.orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
        categoryRepo.delete(selectedCategory);
        return "Deleted successfully";
    }

    @Override
    public CategoryEntity updateCategory(CategoryEntity category, Long categoryId) {
//        List<CategoryEntity> categories = categoryRepo.findAll();
//        CategoryEntity selectedCategory = categories.stream().filter(c -> c.getCategoryId().equals(categoryId)).findFirst().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));
        Optional<CategoryEntity> selectedCategoryOptional = categoryRepo.findById(categoryId);
        CategoryEntity selectedCategory = selectedCategoryOptional.orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
        selectedCategory.setCategoryName(category.getCategoryName());
        return categoryRepo.save(selectedCategory);
    }
}

