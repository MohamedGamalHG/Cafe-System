package com.cafe.services;

import com.cafe.domain.dtos.ProductResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductProviderImp implements ProductProvider {

    private WebClient webClient;

//    List<ProductResponse> hasData = new ArrayList<>();
    public ProductProviderImp(WebClient webClient)
    {
        this.webClient = webClient;
    }

    @Override
    public List<ProductResponse> fetchProductDataByIds(List<Long> ids) {
        /* we make variable hasData to carry data and when call again it give from this data*/
//        if(!hasData.isEmpty())
//            return hasData;
//        else {
            String queryParams = appendIdsForQueryParam(ids);
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/product/retrieveByIds").queryParam("ids", queryParams).build())
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<ProductResponse>>() {
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
