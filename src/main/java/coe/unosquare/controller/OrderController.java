package coe.unosquare.controller;

import coe.unosquare.model.ApiResponse;
import coe.unosquare.model.Order;
import coe.unosquare.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/submit")
    public Mono<ResponseEntity<ApiResponse>> submitOrder(@RequestParam("ordertype") Order.OrderType orderType,
                                                         @RequestParam("price") double price,
                                                         @RequestParam("quantity") int quantity) {
        Order order = new Order(orderType, price, quantity);

        return orderService.processOrder(order)
                .map(response -> ResponseEntity.ok(new ApiResponse(true, "Order processed successfully", response)))
                .onErrorResume(response -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse(false, "Order processing failed", response.getMessage()))));
    }
}