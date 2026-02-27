package Indicator;

import Events.MarketUpdate.MarketUpdate;

import java.util.ArrayDeque;
import java.util.Deque;

public class Delay<R> implements Indicator<R> {

    private final Indicator<R> indicator;
    private final Integer delay;
    private final Deque<R> buffer = new ArrayDeque<>();
    private final R initValue;

    public Delay(Indicator<R> indicator, Integer delay, R initValue) {
        this.indicator = indicator;
        this.delay = delay;
        this.initValue = initValue;
    }

    @Override
    public R update(MarketUpdate update) {
        buffer.addLast(indicator.update(update));
        if (buffer.size() > delay + 1) {
            buffer.removeFirst();
        }
        return buffer.size() > delay ? buffer.getFirst() : initValue;
    }

}
