package Indicator;

import Events.MarketUpdate.MarketUpdate;

public record Printer<R>(Indicator<R> indicator) implements Indicator<R> {
    @Override
    public R update(MarketUpdate update) {
        indicator().update(update);
        System.out.println(value());
        return value();
    }

    @Override
    public R value() {
        return indicator().value();
    }
}
