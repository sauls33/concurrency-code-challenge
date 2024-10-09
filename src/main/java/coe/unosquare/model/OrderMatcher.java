package coe.unosquare.model;

import java.util.Optional;
import java.util.Queue;

public class OrderMatcher {
    private Queue<Order> buyOrders;
    private Queue<Order> sellOrders;

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
