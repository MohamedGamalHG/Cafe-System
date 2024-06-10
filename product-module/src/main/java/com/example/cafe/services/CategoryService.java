package com.example.cafe.services;


import com.example.cafe.handlingException.RecordNotComplete;
import com.example.cafe.handlingException.RecordNotFoundException;
import com.example.cafe.mappers.CategoryDtoConverter;
import com.example.cafe.repositories.CategoryRepo;

import com.example.cafe.domain.dtos.Category;
import com.example.cafe.domain.entities.CategoryJpa;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;
    private final CategoryDtoConverter categoryDtoConverter;
    Logger logger = LoggerFactory.getLogger(CategoryService.class);

    public CategoryService(CategoryRepo categoryRepo,CategoryDtoConverter categoryDtoConverter)
    {
        this.categoryRepo = categoryRepo;
        this.categoryDtoConverter = categoryDtoConverter;
    }
    public List<CategoryJpa> getAll()
    {
        return categoryRepo.findAll();
    }

    public Category findById(Long id)
    {
        Optional<CategoryJpa> categoryJpa = categoryRepo.findById(id);
        if(categoryJpa.isPresent()) {
            Category category = categoryDtoConverter.convert(categoryJpa.get());
            return category;
        }else
            throw new RecordNotFoundException("This Record Is Not Found Of Id = "+id);
    }

    public CategoryJpa insert(Category category)
    {
        try {
            CategoryJpa categoryJpa = categoryDtoConverter.convert(category);
            CategoryJpa categoryJpaInserted = categoryRepo.save(categoryJpa);
            return categoryJpaInserted;
        }catch (Exception ex)
        {
            logger.error(ex.getMessage());
            throw new RecordNotComplete("This Record Is Not Inserted");
        }
    }

    public CategoryJpa update(Category category)
    {
        try {
            Optional<CategoryJpa> categoryJpa = categoryRepo.findById(category.getId());
            if (categoryJpa.isPresent()) {
                CategoryJpa categoryJpaUpdate = categoryDtoConverter.convert(category);
                CategoryJpa categoryJpaUpdated = categoryRepo.save(categoryJpaUpdate);
                return categoryJpaUpdated;
            }
            else
                throw new RecordNotFoundException("This Record Is Not Found Of Id = "+category.getId());
        }catch (Exception ex)
        {
            logger.error(ex.getMessage());
            throw new RecordNotComplete("This Record Is Not Updated");
        }
    }
    public void delete(Long id)
    {
         categoryRepo.deleteById(id);
    }


}
