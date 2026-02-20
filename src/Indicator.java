public interface Indicator<R> {
    void update(MarketUpdate update);
    R value();
}
