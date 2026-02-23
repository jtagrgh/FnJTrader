package Events.MarketUpdate;

import java.time.Instant;

import Events.Bar;

public record BarUpdate(Instant timestamp, String ticker, Bar bar) implements MarketUpdate {
    public PriceUpdate toPriceUpdate () {
        return new PriceUpdate(timestamp(), ticker(), bar().close());
    }
}