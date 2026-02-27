package Indicator;

import Events.MarketUpdate.MarketUpdate;

public class Repeater<R> implements Indicator<R> {

    private Integer updates = 0;

    private final IndicatorMaker<R> maker;
    private final Integer period;

    private Indicator<R> indicator;

    public Repeater(IndicatorMaker<R> maker, Integer period) {
        this.maker = maker;
        this.period = period;
        indicator = maker.make();
    }

    public Delay<R> withDelay(Integer periods, R initValue) {
        return new Delay<R>(this, period * periods, initValue);
    }

    @Override
    public R update(MarketUpdate update) {
        final R value = indicator.update(update);

        ++updates;
        if (updates.equals(period)) {
            indicator = maker.make();
            updates = 0;
        }

        return value;
    }

}
