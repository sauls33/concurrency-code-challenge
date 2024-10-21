package coe.unosquare.service;

import coe.unosquare.model.Order;
import coe.unosquare.model.OrderMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderMatcher orderMatcher;

    @Autowired
    public OrderService(OrderMatcher orderMatcher){
        this.orderMatcher = orderMatcher;
    }

    public Mono<String> processOrder(Order order) {
        return Mono.fromCallable(() -> {
            // Field validations
            validateOrderPrice(order);
            validateOrderQuantity(order);

            // Process the order in a separate thread and return the result
            orderMatcher.addOrder(order);
            orderMatcher.matchOrders();
            return "Order processed: " + order;
        }).subscribeOn(Schedulers.boundedElastic()); // Asynchronous non-blocking processing
    }

    // Method to validate price
    private void validateOrderPrice(Order order){
        Double price = order.getPrice().orElseThrow(() -> new IllegalArgumentException("Price is required"));

        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
    }

    private void validateOrderQuantity(Order order){
        Integer quantity = order.getQuantity().orElseThrow(() -> new IllegalArgumentException("Quantity is required"));

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
    }
}