package coe.unosquare.controller;

import coe.unosquare.model.ApiResponse;
import coe.unosquare.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @PostMapping("/submit")
    public Mono<ResponseEntity<ApiResponse>> submitOrder(@RequestParam("ordertype") Order.OrderType orderType,
                                                    @RequestParam("price") double price,
                                                    @RequestParam("quantity") int quantity) {
        //TODO: process the request calling the service and adequate the response
        return null;
    }
}