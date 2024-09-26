package com.sb.ecommerce.controller;

import com.sb.ecommerce.model.Category;
import com.sb.ecommerce.service.CategoryService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {

//    private  List<Category> categories = new ArrayList<>();

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(path = "/public/categories")
    public List<Category> getCategories() {
        return categoryService.findAll();
    }

    @PostMapping(path = "/public/categories")
    public ResponseEntity<String> addCategory(@Valid @RequestBody Category category) {
        categoryService.save(category);
        return new ResponseEntity<String>(category.toString(),HttpStatus.BAD_REQUEST );
    }
}
