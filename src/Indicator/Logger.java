package Indicator;

import Events.MarketUpdate.MarketUpdate;

import java.util.ArrayList;
import java.util.List;

public class Logger<R> implements Indicator<R> {

    private final Indicator<R> indicator;
    private final List<R> log = new ArrayList<>();

    public Logger(Indicator<R> indicator) {
        this.indicator = indicator;
    }

    public List<R> log() {
        return List.copyOf(log);
    }

    @Override
    public R update(MarketUpdate update) {
        final R value = indicator.update(update);
        log.add(value);
        return value;
    }

}
