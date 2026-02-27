package Indicator;

import Events.MarketUpdate.MarketUpdate;

import java.util.HashSet;
import java.util.Set;

public class UpdateOnce<R> implements Indicator<R> {
    private final Set<Integer> cache = new HashSet<Integer>();
    private final Indicator<R> indicator;
    private R value = null;

    public UpdateOnce(Indicator<R> indicator) {
        this.indicator = indicator;
    }

    @Override
    public R update(MarketUpdate update) {
        final Integer index = update.index();

        if (!cache.contains(index)) {
            value = indicator.update(update);
            cache.add(index);
        }

        return value;
    }

}
