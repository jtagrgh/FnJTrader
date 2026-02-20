import java.time.Instant;

public class MarketUpdate {
    private final Instant timestamp;

    public MarketUpdate(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}