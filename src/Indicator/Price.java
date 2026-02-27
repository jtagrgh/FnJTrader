package Indicator;

import Events.MarketUpdate.BarUpdate;
import Events.MarketUpdate.MarketUpdate;
import Events.MarketUpdate.PriceUpdate;

public class Price implements Indicator<Double> {

    private Double price = 0.0;

    @Override
    public Double update(MarketUpdate update) {
        return switch (update) {
            case PriceUpdate p -> p.price();
            case BarUpdate b -> b.bar().close();
        };
    }

}
