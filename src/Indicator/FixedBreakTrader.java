package Indicator;

import Events.Order;

import Events.MarketUpdate.*;

public record FixedBreakTrader(Indicator<FixedBreakIndicator.R> indicator) implements Indicator<Order> {
    @Override
    public Order update(MarketUpdate update) {
        indicator.update(update);
        return value();
    }

    @Override
    public Order value() {
        return switch(indicator.value()){
            case ABOVE -> new Order(1);
            case BELOW -> new Order(-1);
        };
    }
}
