package Events.MarketUpdate;

import java.time.Instant;

public record PriceUpdate(Integer index, String ticker, Double price) implements MarketUpdate {
}
