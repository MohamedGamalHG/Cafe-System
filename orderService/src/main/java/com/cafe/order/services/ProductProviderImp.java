package com.cafe.order.services;

import com.cafe.order.domain.dtos.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ProductProviderImp implements ProductProvider {

    private WebClient webClient;
    private List<Product> products;
    public ProductProviderImp(WebClient webClient)
    {
        this.webClient = webClient;
    }

    @Override
    public List<Product> fetchProductDataByIds(List<Long> ids) {
            String queryParams = appendIdsForQueryParam(ids);
            this.products =  webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/api/product/retrieveByIds").queryParam("ids", queryParams).build())
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Product>>() {
                    }).block();

            return this.products;
    }


    public List<Product> fetchedProduct()
    {
        return this.products;
    }
    private String appendIdsForQueryParam(List<Long> ids)
    {
        StringBuilder sb = new StringBuilder();
        for (Long id : ids) {
            sb.append(id).append(",");
        }
        return sb.substring(0, sb.length() - 1); // Remove trailing "&"
    }
}
