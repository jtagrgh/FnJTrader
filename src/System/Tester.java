package System;

import Events.MarketUpdate.MarketUpdate;
import Indicator.Indicator;

import java.util.stream.Stream;

public record Tester(Stream<MarketUpdate> marketStream, Indicator<?> indicator) {
    public void run() {
        marketStream.forEach(indicator()::update);
    }
}
