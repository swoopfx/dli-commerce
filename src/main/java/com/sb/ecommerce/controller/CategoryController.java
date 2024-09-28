package com.sb.ecommerce.controller;

import com.sb.ecommerce.dto.CategoryDto;
import com.sb.ecommerce.dto.CategoryResponse;
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
    public ResponseEntity<CategoryResponse> getCategories(@RequestParam(name = "pageNumber", defaultValue = "0", required = false  ) Integer pageNumber,
                                                          @RequestParam(name = "limit", defaultValue = "3", required = false) Integer limit) {
      CategoryResponse categoryResponse = categoryService.getAllcategory(pageNumber, limit);
        return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/public/categories")
    public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto category) {
        CategoryDto categoryDto = categoryService.createCategory(category);
        return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.CREATED );
    }

    @PutMapping(path = "/public/categories/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
                                                      @PathVariable Long categoryId) {
        CategoryDto categoryResponse = categoryService.updateCategory(categoryDto, categoryId);
        return  new  ResponseEntity<CategoryDto>(categoryResponse, HttpStatus.CREATED);

    }
}
