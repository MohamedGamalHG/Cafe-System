package com.cafe.order.config;

import com.cafe.order.services.PriceService;
import com.cafe.order.services.PriceServiceImp;
import com.cafe.order.services.ProductProvider;
import com.cafe.order.services.ProductProviderImp;
import org.modelmapper.ModelMapper;
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
