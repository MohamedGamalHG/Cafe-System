package com.example.ordersystem.services;

import com.example.ordersystem.domain.dtos.ProductOrderRetrieveResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements ServiceProvider{

    private WebClient webClient;
    public ProductService(WebClient webClient)
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

    public List<ProductOrderRetrieveResponse> FetchDataFromProduct(String path, List<Long> ids) {
        StringBuilder sb = new StringBuilder();
        //sb.append("ids=");
        for (Long id : ids) {
            sb.append(id).append(",");
        }
        String queryParams = sb.substring(0, sb.length() - 1); // Remove trailing "&"
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(path).queryParam("ids",queryParams).build() )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ProductOrderRetrieveResponse>>() {}).block();

    }

        @Override
    public Optional<?> fetchData(int[] ids) {

        return Optional.empty();
    }
}
