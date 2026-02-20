public class FixedBreakTrader extends Indicator<Order> {
    private final FixedBreakIndicator indicator;

    public FixedBreakTrader(FixedBreakIndicator indicator) {
        this.indicator = indicator;
    }

    @Override
    public void update(MarketUpdate update) {
        indicator.update(update);
    }

    @Override
    public Order value() {
        return switch(indicator.value()){
            case ABOVE -> new Order(1);
            case BELOW -> new Order(-1);
        };
    }
}
