package com.example.cafe.mappers;

import com.example.cafe.domain.dtos.Category;
import com.example.cafe.domain.dtos.Product;
import com.example.cafe.domain.entities.CategoryJpa;
import com.example.cafe.domain.entities.ProductJpa;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoConverter {

    private final ModelMapper modelMapper;

    public ProductDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
//        TypeMap<Category, CategoryJpa> type = this.modelMapper.createTypeMap(Category.class, CategoryJpa.class);
//        type.addMappings(mapper -> {
//            mapper.map(src -> src.getCategoryName(), CategoryJpa::setName);
//            //mapper.map(src -> src.getId(), CategoryJpa::setId);
//        });
    }
    public Product convert(ProductJpa productJpa)
    {
        Product product = modelMapper.map(productJpa, Product.class);
        return product;
    }
    public ProductJpa convert(Product product)
    {
        ProductJpa productJpa = modelMapper.map(product, ProductJpa.class);
        return productJpa;
    }

}
