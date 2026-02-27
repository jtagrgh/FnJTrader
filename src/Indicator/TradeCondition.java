package Indicator;

@FunctionalInterface
public interface TradeCondition<R> {
    Boolean check(R value);
}
