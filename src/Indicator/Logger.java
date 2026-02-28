package Indicator;

import Events.MarketUpdate.MarketUpdate;

import java.util.ArrayList;
import java.util.List;

public class Logger<R> implements Indicator<R> {

    private final Indicator<R> indicator;
    private final String header;
    private final List<R> log = new ArrayList<>();

    public Logger(Indicator<R> indicator, String header) {
        this.indicator = indicator;
        this.header = header;
    }

    public List<R> log() {
        return List.copyOf(log);
    }

    public int size() {
        return log.size();
    }

    public String header() {
        return header;
    }

    public R get(int index) {
        return log.get(index);
    }

    @Override
    public R update(MarketUpdate update) {
        final R value = indicator.update(update);
        log.add(value);
        return value;
    }

}
