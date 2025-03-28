package com.sit.ecom.repository;

import com.sit.ecom.entities.CategoryEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Long> {

    CategoryEntity findByCategoryName(String categoryName);
}
