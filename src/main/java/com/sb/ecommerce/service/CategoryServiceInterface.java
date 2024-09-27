package com.sb.ecommerce.service;

import com.sb.ecommerce.dto.CategoryResponse;
import com.sb.ecommerce.model.Category;

import java.util.List;

public interface CategoryServiceInterface {


    CategoryResponse getAllcategory();
    void save(Category category);
}
