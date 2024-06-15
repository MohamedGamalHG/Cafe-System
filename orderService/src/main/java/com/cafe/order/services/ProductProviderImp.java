package com.cafe.order.services;

import com.cafe.order.domain.dtos.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ProductProviderImp implements ProductProvider {

    private WebClient webClient;

//    List<ProductResponse> hasData = new ArrayList<>();
    public ProductProviderImp(WebClient webClient)
    {
        this.webClient = webClient;
    }

    @Override
    public List<Product> fetchProductDataByIds(List<Long> ids) {
        /* we make variable hasData to carry data and when call again it give from this data*/
//        if(!hasData.isEmpty())
//            return hasData;
//        else {
            String queryParams = appendIdsForQueryParam(ids);
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/product/retrieveByIds").queryParam("ids", queryParams).build())
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Product>>() {
                    }).block();
//        }
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
