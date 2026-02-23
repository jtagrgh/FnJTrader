package Indicator;

import Events.MarketUpdate.MarketUpdate;

public interface Indicator<R> {
    R update(MarketUpdate update);
    R value();
}
