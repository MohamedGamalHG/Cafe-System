package com.example.cafe.mappers;

import com.example.cafe.domain.dtos.Category;
import com.example.cafe.domain.entities.CategoryJpa;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoConverter {

    private final ModelMapper modelMapper;

    public CategoryDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        TypeMap<Category, CategoryJpa> type = this.modelMapper.createTypeMap(Category.class, CategoryJpa.class);
        type.addMappings(mapper -> {
            mapper.map(src -> src.getCategoryName(), CategoryJpa::setName);
            //mapper.map(src -> src.getId(), CategoryJpa::setId);
        });
    }
    public Category convert(CategoryJpa categoryJpa)
    {
        Category category = modelMapper.map(categoryJpa, Category.class);
        return category;
    }
    public CategoryJpa convert(Category category)
    {
        CategoryJpa categoryJpa = modelMapper.map(category, CategoryJpa.class);
        return categoryJpa;
    }

}
