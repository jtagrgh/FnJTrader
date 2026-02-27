package Indicator;

import Events.MarketUpdate.MarketUpdate;

public class DownSample<R> implements Indicator<R> {

    private final Integer length;
    private final Indicator<R> indicator;
    private R value;
    private Integer updates = 0;

    public DownSample(Indicator<R> indicator, Integer length, R initValue) {
        this.length = length;
        this.indicator = indicator;
        this.value = initValue;
    }

    @Override
    public R update(MarketUpdate update) {
        ++updates;
        if (updates.equals(length)) {
            value = indicator.update(update);
            updates = 0;
        } else {
            indicator.update(update);
        }
        return value;
    }

}
