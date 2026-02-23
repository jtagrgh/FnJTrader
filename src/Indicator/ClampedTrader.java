package Indicator;

import Events.Order;
import Events.MarketUpdate.MarketUpdate;

public class ClampedTrader implements Indicator<Order> {
    private final Indicator<Order> indicator;
    private final Integer upperLimit;
    private final Integer lowerLimit;
    private Integer position = 0;

    public ClampedTrader(Integer lowerLimit, Integer upperLimit, Indicator<Order> indicator) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.indicator = indicator;
    }

    @Override
    public Order update(MarketUpdate update) {
        indicator.update(update);
        return value();
    }

    @Override
    public Order value() {
        final Order order = indicator.value();
        final int newPosition = position + order.amount();
        if (newPosition <= upperLimit && newPosition >= lowerLimit) {
            position = newPosition;
            return order;
        }
        return new Order(0);
    }
}
