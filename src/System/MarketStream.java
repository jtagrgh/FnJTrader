package System;

import Events.MarketUpdate.MarketUpdate;

import java.util.stream.Stream;

public interface MarketStream extends Stream<MarketUpdate> {
}
