package Indicator;

import Events.MarketUpdate.MarketUpdate;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Buffer<R> implements Indicator<List<R>> {

    private final int maxSize;
    private final Indicator<R> indicator;
    private final Deque<R> buffer = new ArrayDeque<R>();

    public Buffer(int maxSize, Indicator<R> indicator) {
        this.maxSize = maxSize;
        this.indicator = indicator;
    }

    @Override
    public List<R> update(MarketUpdate update) {
        buffer.addLast(indicator.update(update));
        if (buffer.size() > maxSize) {
            buffer.removeFirst();
        }
        return List.copyOf(buffer);
    }
}
