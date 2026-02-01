package com.demo.playground.test;

import java.util.List;
import java.util.stream.Collectors;


class Order {
    private String orderId;
    private List<String> items;

    public Order(String orderId, List<String> items) {
        this.orderId = orderId;
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
public class StreamSamples {
    public static void main(String[] args) {
        Order order1 = new Order("123", List.of("item1", "item2"));
        Order order2 = new Order("456", List.of("item3", "item4"));
        Order order3 = new Order("789", List.of("item1", "item3"));
        List<Order> orders = List.of(order1, order2, order3);

        List<String> distinctItems = orders.stream().flatMap(x -> x.getItems().stream())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(distinctItems);
    }
}

