package Indicator;

import Events.MarketUpdate.MarketUpdate;
import Events.Order;

import static java.lang.Math.clamp;

public class Pyramid implements Indicator<Order> {

    private final Indicator<Order> indicator;
    private final Integer cap;
    private Integer position = 0;

    public Pyramid(Indicator<Order> indicator, Integer cap) {
        this.indicator = indicator;
        this.cap = cap;
    }

    public static IndicatorPipe<Order,Order> withCap(Integer cap) {
        return (Indicator<Order> s) -> new Pyramid(s, cap);
    }

    @Override
    public Order update(MarketUpdate update) {
        final Order order = indicator.update(update);
        final int amount = clamp(order.amount(), -position, cap - position);
        position += amount;
        return new Order(amount);
    }
}
