import java.time.Instant;

public class PriceUpdate extends MarketUpdate {
    private final String ticker;
    private final Double price;

    public PriceUpdate(Instant timestamp, String ticker, Double price) {
        super(timestamp);
        this.ticker = ticker;
        this.price = price;
    }

    public String getTicker() {
        return ticker;
    }

    public Double getPrice() {
        return price;
    }
}
