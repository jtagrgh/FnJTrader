package Events;

import Events.MarketUpdate.PriceUpdate;

import java.time.Instant;

public record Bar(Instant openTime, Double open, Double high, Double low, Double close) {

    public static class Builder {
        private final Double open;
        private final Instant firstTimestamp;
        private Double high = 0.0;
        private Double low = 0.0;
        private Double close = 0.0;

        public Builder(PriceUpdate update) {
            open = update.price();
            firstTimestamp = update.timestamp();
            update(update);
        }

        public void update(PriceUpdate update) {
            Double price = update.price();
            if (price > high) {
                high = price;
            }
            if (price < low) {
                low = price;
            }
            close = price;
        }

        public Bar build() {
            return new Bar(firstTimestamp, open, high, low, close);
        }
    }
}
