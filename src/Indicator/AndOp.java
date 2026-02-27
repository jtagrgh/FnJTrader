package Indicator;

import Events.MarketUpdate.MarketUpdate;
import Events.MarketUpdate.PriceUpdate;

public class AndOp implements Indicator<Boolean> {

    private final Indicator<Boolean> left_indicator;
    private final Indicator<Boolean> right_indicator;

    public AndOp(Indicator<Boolean> leftIndicator, Indicator<Boolean> rightIndicator) {
        left_indicator = leftIndicator;
        right_indicator = rightIndicator;
    }

    @Override
    public Boolean update(MarketUpdate update) {
        return left_indicator.update(update) && right_indicator.update(update);
    }
}
