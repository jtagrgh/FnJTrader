import java.time.Instant;

public class BarIndicator implements Indicator<Bar> {
    private final Integer duration;

    private BarBuilder workingBar = null;
    private Bar lastCompleteBar = new Bar(Instant.now(), 0.0,0.0,0.0,0.0);
    private Integer updates = 0;

    public BarIndicator(Integer duration) {
        if (duration < 1) {
            throw new IllegalArgumentException("Duration must be at least 1.");
        }
        this.duration = duration;
    }

    @Override
    public void update(MarketUpdate update) {
        switch (update) {
            case PriceUpdate p -> handlePriceUpdate(p);
            default -> { }
        }
    }

    @Override
    public Bar value() {
        return lastCompleteBar;
    }

    private void handlePriceUpdate(PriceUpdate update) {
        updates += 1;
        if (updates == 1) {
            workingBar = new BarBuilder(update);
        } else {
            workingBar.update(update);
        }
        if (updates.equals(duration)) {
            lastCompleteBar = workingBar.build();
            updates = 0;
        }
    }
}
