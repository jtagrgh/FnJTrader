package Indicator;

import Events.Bar;
import Events.MarketUpdate.*;

import java.time.Instant;

public class BarIndicator implements Indicator<Bar> {

    private Bar.Builder builder = null;

    @Override
    public Bar update(MarketUpdate update) {
        return switch (update) {
            case BarUpdate barUpdate -> updateBar(barUpdate.bar().close());
            case PriceUpdate priceUpdate -> updateBar(priceUpdate.price());
        };
    }

    private Bar updateBar(Double price) {
        if (builder == null) {
            builder = new Bar.Builder(price);
        } else {
            builder.update(price);
        }
        return builder.build();
    }
}
