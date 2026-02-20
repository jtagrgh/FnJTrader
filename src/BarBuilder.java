import java.time.Instant;

public class BarBuilder {
    private final Double open;
    private final Instant firstTimestamp;
    private Double high = 0.0;
    private Double low = 0.0;
    private Double close = 0.0;

    public BarBuilder(PriceUpdate update) {
        open = update.getPrice();
        firstTimestamp = update.getTimestamp();
        update(update);
    }

    public void update(PriceUpdate update) {
        Double price = update.getPrice();
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
