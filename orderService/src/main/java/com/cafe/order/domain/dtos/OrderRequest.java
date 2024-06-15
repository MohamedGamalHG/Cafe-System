package com.cafe.order.domain.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderRequest {
   List<OrderRequestList> orderRequestLists = new ArrayList<>();
}
