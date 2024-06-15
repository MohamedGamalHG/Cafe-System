package com.cafe.order.enums;

public enum OrderStatus  {
    Placed(0) , // 0
    Preparing(1), // 1
    Ready(2), // 2
    Delivered(3); // 3

    private int value;

    OrderStatus(int value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value+"";
    }
}
