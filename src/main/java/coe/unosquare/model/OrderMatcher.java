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

        Order.OrderType orderType = order.getType().orElseThrow(() -> new IllegalArgumentException("Order type is required"));

        if(orderType == Order.OrderType.BUY){
            buyOrders.add(order);
        } else if(orderType == Order.OrderType.SELL){
            sellOrders.add(order);
        }
    }

    // Use Optional to wrap the next buy order (or empty if none).
    public Optional<Order> getNextBuyOrder() {
        return Optional.ofNullable(buyOrders.peek());
    }

    // Use Optional to wrap the next sell order (or empty if none).
    public Optional<Order> getNextSellOrder() {
        return Optional.ofNullable(sellOrders.peek());
    }

    /* checks for pairs of buy and sell orders with compatible prices (where the buy order price is greater than or
    equal to the sell order price) and removes them from the queues once matched, simulating a trade between
    those orders.*/
    public void matchOrders() {
        while(!buyOrders.isEmpty() || !sellOrders.isEmpty()){
            Order buyOrder = buyOrders.peek();
            Order sellOrder = sellOrders.peek();

            double buyPrice = buyOrder.getPrice().orElse(0.0);
            double sellPrice = sellOrder.getPrice().orElse(0.0);

            if(buyPrice >= sellPrice){
                buyOrders.poll();
                sellOrders.poll();
            }
        }
    }
}
