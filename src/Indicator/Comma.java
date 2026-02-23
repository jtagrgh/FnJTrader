package Indicator;

import Events.MarketUpdate.MarketUpdate;

public record Comma<R>(Indicator<R> valueIndicator, Indicator... indicators) implements Indicator<R> {
    @Override
    public R update(MarketUpdate update) {
        for (Indicator indicator : indicators()) {
            indicator.update(update);
        }
        return valueIndicator.update(update);
    }

    @Override
    public R value() {
        return valueIndicator.value();
    }
}
