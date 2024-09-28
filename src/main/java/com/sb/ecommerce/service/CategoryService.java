package com.sb.ecommerce.service;

import com.sb.ecommerce.dto.CategoryDto;
import com.sb.ecommerce.dto.CategoryResponse;
import com.sb.ecommerce.exceptions.EmptyDataException;
import com.sb.ecommerce.model.Category;
import com.sb.ecommerce.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public CategoryResponse getAllcategory(Integer pageNumber, Integer limit) {
//        return categoryRepository.findAll();
        Pageable categoryPage = PageRequest.of(pageNumber, limit);
        Page<Category> categoriesPage = categoryRepository.findAll(categoryPage);
        List<Category> categories = categoriesPage.getContent();
        if (categories.isEmpty()) {
            throw new EmptyDataException("No Data");
        }
        List<CategoryDto> categoryDto = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class)).toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDto);
        return categoryResponse;
    }

    @Override
    public CategoryDto createCategory(CategoryDto category) {
        Category categoryEntity = modelMapper.map(category, Category.class);

        List<Category> serachCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if (serachCategory != null) {
            throw new EmptyDataException("Category already exists");
        }
        Category savedCategory = categoryRepository.save(categoryEntity);

//        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
//        categoryResponse.setContent(categoryDto);
//        return categoryResponse;
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
//        Category categoryEntity = modelMapper.map(categoryId, Category.class);
        Category categoryEntity = categoryRepository.findById(categoryId).orElse(null);
        if (categoryEntity == null) {
            throw new EmptyDataException("Category not found");
        }
        categoryEntity.setCategoryName(categoryDto.getCategoryName());
        categoryRepository.save(categoryEntity);
        return modelMapper.map(categoryEntity, CategoryDto.class);
    }
}
