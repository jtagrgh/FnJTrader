package Events.MarketUpdate;

import java.time.Instant;

public sealed interface MarketUpdate permits BarUpdate, PriceUpdate {
    public Instant timestamp();
}