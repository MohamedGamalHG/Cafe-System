package com.cafe.config;

import com.cafe.domain.dtos.Order;
import com.cafe.services.PriceService;
import com.cafe.services.PriceServiceImp;
import com.cafe.services.ProductProvider;
import com.cafe.services.ProductProviderImp;
import liquibase.integration.spring.SpringLiquibase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GeneralConfig {

    private final WebClient webClient;

    public GeneralConfig(WebClient webClient){
        this.webClient = webClient;
    }
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
    public PriceService priceService(WebClient webClient)
    {
        return new PriceServiceImp(productProvider(webClient));
    }
}
