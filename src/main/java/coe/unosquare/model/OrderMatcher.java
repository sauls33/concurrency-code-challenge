package coe.unosquare.model;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class OrderMatcher {
    private final Queue<Order> buyOrders;
    private final Queue<Order> sellOrders;

    public OrderMatcher(){
        this.buyOrders = new ConcurrentLinkedQueue<>();
        this.sellOrders = new ConcurrentLinkedQueue<>();
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
        while(!buyOrders.isEmpty() && !sellOrders.isEmpty()){
            Optional<Order> optionalBuyOrder = getNextBuyOrder();
            Optional<Order> optionalSellOrder = getNextSellOrder();

            if(optionalBuyOrder.isPresent() && optionalSellOrder.isPresent()) {
                Order buyOrder = optionalBuyOrder.get();
                Order sellOrder = optionalSellOrder.get();

                if (buyOrder.getPrice().orElse(0.0) >= sellOrder.getPrice().orElse(0.0)) {
                    buyOrders.poll();
                    sellOrders.poll();
                } else {
                    break;
                }
            } else {
                break;
            }
        }
    }
}
