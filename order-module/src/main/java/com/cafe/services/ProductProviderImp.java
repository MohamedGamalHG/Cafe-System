package com.cafe.services;

import com.cafe.domain.dtos.ProductResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class ProductProviderImp implements ProductProvider {

    private WebClient webClient;
    public ProductProviderImp(WebClient webClient)
    {
        this.webClient = webClient;
    }

//    public Mono<ProductOrderRetrieveResponse> FetchDataFromProduct(String path, List<Long> ids) {
//        StringBuilder sb = new StringBuilder();
//        //sb.append("ids=");
//        for (Long id : ids) {
//            sb.append(id).append(",");
//        }
//        String queryParams = sb.substring(0, sb.length() - 1); // Remove trailing "&"
//
//
////        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
////        params.add("ids", ids.stream().map(Object::toString).collect(Collectors.toList()));
//
//
//        return webClient.get()
//                .uri(uriBuilder ->
//                    uriBuilder.path(path).queryParam("ids",queryParams).build()
////                    uriBuilder.path(path).query(queryParams).build()
//                )
//                .retrieve()
//                .bodyToMono(ProductOrderRetrieveResponse.class);
//
//    }

    public List<ProductResponse> FetchDataFromProduct(String path, List<Long> ids) {
        StringBuilder sb = new StringBuilder();
        //sb.append("ids=");
        for (Long id : ids) {
            sb.append(id).append(",");
        }
        String queryParams = sb.substring(0, sb.length() - 1); // Remove trailing "&"
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(path).queryParam("ids",queryParams).build() )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ProductResponse>>() {}).block();

    }

        @Override
    public List<ProductResponse> fetchProductDataByIds(List<Long> ids) {
            String queryParams = appendIdsForQueryParam(ids);
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/product/retrieveByIds").queryParam("ids",queryParams).build() )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<ProductResponse>>() {}).block();
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
