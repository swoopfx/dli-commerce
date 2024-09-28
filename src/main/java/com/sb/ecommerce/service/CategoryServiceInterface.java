package com.sb.ecommerce.service;

import com.sb.ecommerce.dto.CategoryDto;
import com.sb.ecommerce.dto.CategoryResponse;

public interface CategoryServiceInterface {


    CategoryResponse getAllcategory(Integer pageNumber, Integer limit);
//    void save(Category category);
     CategoryDto createCategory(CategoryDto category);
     CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);
}
