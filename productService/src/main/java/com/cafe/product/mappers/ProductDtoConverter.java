package com.cafe.product.mappers;

import com.cafe.product.domain.dtos.Product;
import com.cafe.product.domain.entities.ProductJpa;
import org.modelmapper.ModelMapper;
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
