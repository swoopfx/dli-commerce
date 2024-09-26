package com.sb.ecommerce.service;

import com.sb.ecommerce.model.Category;

import java.util.List;

public interface CategoryServiceInterface {

    List<Category> findAll();
    void save(Category category);
}
