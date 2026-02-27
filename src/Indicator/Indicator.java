package Indicator;

import Events.MarketUpdate.MarketUpdate;
import Events.MarketUpdate.PriceUpdate;

public interface Indicator<R> {
    R update(MarketUpdate update);

    default <T> Indicator<T> with(IndicatorPipe<R,T> pipe) {
        return pipe.pipe(this);
    }
}
