package System;

import Indicator.Indicator;

public record Tester(MarketStream marketStream, Indicator<Void> indicator) {
    public void run() {
        marketStream.forEach(indicator()::update);
    }
}
