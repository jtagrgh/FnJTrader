public class FixedBreakIndicator implements Indicator<FixedBreakIndicator.R> {
    private final double breakPrice;
    private R status;

    public FixedBreakIndicator(double breakPrice) {
        this.breakPrice = breakPrice;
        this.status = R.BELOW;
    }

    @Override
    public R update(MarketUpdate update) {
        switch(update) {
            case PriceUpdate p -> handlePriceUpdate(p);
            case BarUpdate b -> handlePriceUpdate(b.toPriceUpdate());
        }
        return value();
    }

    private void handlePriceUpdate(PriceUpdate update) {
        if (update.price() >= breakPrice) {
            status = R.ABOVE;
        } else {
            status = R.BELOW;
        }
    }

    @Override
    public R value() {
        return status;
    }

    public enum R {
        ABOVE, BELOW
    }
}
