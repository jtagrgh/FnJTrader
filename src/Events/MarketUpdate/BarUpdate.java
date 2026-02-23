package Events.MarketUpdate;

import java.time.Instant;

import Events.Bar;

public record BarUpdate(Integer index, String ticker, Bar bar) implements MarketUpdate {
    public PriceUpdate toPriceUpdate () {
        return new PriceUpdate(index(), ticker(), bar().close());
    }
}