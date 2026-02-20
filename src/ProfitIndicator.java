public class ProfitIndicator extends Indicator<Double> {
    private final Indicator<Order> indicator;
    private Order lastAction = new Order(0);
    private Double profit = 0.0;

    public ProfitIndicator(Indicator<Order> indicator) {
        this.indicator = indicator;
    }

    @Override
    public void update(MarketUpdate update) {
        indicator.update(update);
        switch (update) {
            case PriceUpdate p -> handlePriceUpdate(p);
            default -> {}
        }
    }

    private void handlePriceUpdate(PriceUpdate update) {
        profit -= lastAction.amount() * update.getPrice();
        lastAction = indicator.value();
    }

    @Override
    public Double value() {
        return profit;
    }
}
