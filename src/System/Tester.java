package System;

import Events.MarketUpdate.MarketUpdate;
import Events.MarketUpdate.PriceUpdate;
import Indicator.Indicator;

import java.util.stream.Stream;

public record Tester(Stream<PriceUpdate> marketStream, Indicator<?> indicator) {
    public void run() {
        marketStream.forEach(indicator()::update);
    }
}
