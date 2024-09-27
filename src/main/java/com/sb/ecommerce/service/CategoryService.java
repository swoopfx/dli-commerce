package com.sb.ecommerce.service;

import com.sb.ecommerce.dto.CategoryDto;
import com.sb.ecommerce.dto.CategoryResponse;
import com.sb.ecommerce.exceptions.EmptyDataException;
import com.sb.ecommerce.model.Category;
import com.sb.ecommerce.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryService implements  CategoryServiceInterface{

    private List<Category> categories = new ArrayList<>();

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllcategory() {
//        return categoryRepository.findAll();
        List<Category> categories = this.categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new EmptyDataException("");
        }
        List<CategoryDto> categoryDto = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class)).toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDto);
        return categoryResponse;
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }
}
