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
            // Process the order in a separate thread and return the result
            orderMatcher.addOrder(order);
            orderMatcher.matchOrders();
            return "Order processed: " + order;
        }).subscribeOn(Schedulers.boundedElastic()); // Asynchronous non-blocking processing
    }
}