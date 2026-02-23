package Indicator;

import Events.Order;

import Events.MarketUpdate.*;

public class FixedBreakTrader implements Indicator<Order> {
    private final FixedBreakIndicator indicator;

    public FixedBreakTrader(FixedBreakIndicator indicator) {
        this.indicator = indicator;
    }

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
