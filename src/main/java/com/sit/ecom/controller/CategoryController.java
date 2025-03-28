package com.sit.ecom.controller;

import com.sit.ecom.entities.CategoryEntity;
import com.sit.ecom.service.CategoryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);


    @GetMapping("/public/categories")
    public ResponseEntity<List<CategoryEntity>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryEntity category){
        //the valid annnotation checking whether the incoming request is fullfilling the constraint defined in the entity- give (400 bad request)
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
            String status = categoryService.deleteCategory(categoryId);
              return ResponseEntity.ok(status);

    }

    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody CategoryEntity category, @PathVariable Long categoryId){
            CategoryEntity savedCategory = categoryService.updateCategory(category, categoryId);
            return new ResponseEntity<>("update successful", HttpStatus.OK);

    }

}
