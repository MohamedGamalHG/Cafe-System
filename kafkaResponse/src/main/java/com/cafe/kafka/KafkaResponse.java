package com.cafe.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KafkaResponse {
    private String productName;
    private int quantity;

    public KafkaResponse(Builder builder)
    {
        productName = builder.productName;
        quantity = builder.quantity;
    }
    public static class Builder{
        private String productName;
        private int quantity;

        public Builder setProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }
        public KafkaResponse build()
        {
            return  new KafkaResponse(productName,quantity);
        }
    }
}
