import java.time.Instant;

public record BarUpdate(Instant timestamp, String ticker, Bar bar) implements MarketUpdate {
    public PriceUpdate toPriceUpdate () {
        return new PriceUpdate(timestamp(), ticker(), bar().close());
    }
}