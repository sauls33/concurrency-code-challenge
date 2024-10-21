package coe.unosquare.model;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

@Component
public class OrderMatcher {
    private Queue<Order> buyOrders;
    private Queue<Order> sellOrders;

    public OrderMatcher(){
        this.buyOrders = new ConcurrentLinkedDeque<>();
        this.sellOrders = new ConcurrentLinkedDeque<>();
    }

    //Validate the order type and add into the corresponding q.
    public void addOrder(Order order) {
        //TODO
    }

    // Use Optional to wrap the next buy order (or empty if none).
    public Optional<Order> getNextBuyOrder() {
        //TODO
        return null;
    }

    // Use Optional to wrap the next sell order (or empty if none).
    public Optional<Order> getNextSellOrder() {
        //TODO
        return null;
    }

    /* checks for pairs of buy and sell orders with compatible prices (where the buy order price is greater than or
    equal to the sell order price) and removes them from the queues once matched, simulating a trade between
    those orders.*/
    public void matchOrders() {
        //TODO
    }
}
