public abstract class Indicator<R> {
    public abstract void update(MarketUpdate update);
    public abstract R value();
}
