package com.cafe.order.config;

import com.cafe.order.services.PriceService;
import com.cafe.order.services.PriceServiceImp;
import com.cafe.order.services.ProductProvider;
import com.cafe.order.services.ProductProviderImp;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;

@Configuration
@AllArgsConstructor
public class GeneralConfig {

    private final WebClient webClient;
    @Bean
    public ModelMapper modelMapper()
    {
        return  new ModelMapper();
    }

    @Bean
    public ProductProvider productProvider(WebClient webClient)
    {
        return new ProductProviderImp(webClient);
    }

    @Bean
    public PriceService priceService(ProductProvider productProvider)
    {
        return new PriceServiceImp(productProvider,new HashMap<>());
    }
}
