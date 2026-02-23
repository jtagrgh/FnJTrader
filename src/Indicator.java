public interface Indicator<R> {
    R update(MarketUpdate update);
    R value();
}
