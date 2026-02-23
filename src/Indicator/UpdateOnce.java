package Indicator;

import Events.MarketUpdate.MarketUpdate;

import java.util.HashSet;
import java.util.Set;

public class UpdateOnce<R> implements Indicator<R> {
    private final Set<Integer> cache = new HashSet<Integer>();
    private final Indicator<R> indicator;

    public UpdateOnce(Indicator<R> indicator) {
        this.indicator = indicator;
    }

    @Override
    public R update(MarketUpdate update) {
        if (!cache.contains(update.index())) {
            indicator.update(update);
        }
        return indicator.value();
    }

    @Override
    public R value() {
        return indicator.value();
    }
}
