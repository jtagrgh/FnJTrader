package Events.MarketUpdate;

import java.time.Instant;

public record PriceUpdate(Instant timestamp, String ticker, Double price) implements MarketUpdate {
}
