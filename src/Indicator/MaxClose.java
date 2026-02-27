package Indicator;

import Events.MarketUpdate.BarUpdate;
import Events.MarketUpdate.MarketUpdate;
import Events.MarketUpdate.PriceUpdate;

public class MaxClose implements Indicator<Double> {
    private Double maxClose = 0.0;

    @Override
    public Double update(MarketUpdate update) {
        return switch (update) {
            case PriceUpdate p -> handleUpdate(p.price());
            case BarUpdate b -> handleUpdate(b.bar().close());
        };
    }

    private Double handleUpdate(Double close) {
        if (close > maxClose) {
            maxClose = close;
        }
        return maxClose;
    }

}
