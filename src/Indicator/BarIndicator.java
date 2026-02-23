package Indicator;

import Events.Bar;
import Events.MarketUpdate.*;

import java.time.Instant;

public class BarIndicator implements Indicator<Bar> {
    private final Integer duration;

    private Bar.Builder workingBar = null;
    private Bar lastCompleteBar = new Bar(0.0,0.0,0.0,0.0);
    private Integer updates = 0;

    public BarIndicator(Integer duration) {
        if (duration < 1) {
            throw new IllegalArgumentException("Duration must be at least 1.");
        }
        this.duration = duration;
    }

    @Override
    public Bar update(MarketUpdate update) {
        switch (update) {
            case PriceUpdate p -> handlePriceUpdate(p);
            default -> { }
        }
        return value();
    }

    @Override
    public Bar value() {
        return lastCompleteBar;
    }

    private void handlePriceUpdate(PriceUpdate update) {
        updates += 1;
        if (updates == 1) {
            workingBar = new Bar.Builder(update);
        } else {
            workingBar.update(update);
        }
        if (updates.equals(duration)) {
            lastCompleteBar = workingBar.build();
            updates = 0;
        }
    }
}
