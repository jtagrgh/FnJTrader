package Indicator;

import Events.MarketUpdate.MarketUpdate;
import Events.MarketUpdate.PriceUpdate;
import Events.Order;

public class PrevPeriodHiReversal implements Indicator<Order> {

    private final Indicator<Order> indicator;

    public PrevPeriodHiReversal(Integer period) {

        final Indicator<Double> yesterdayClose = new Repeater<Double>(MaxClose::new, period)
                .with((s) -> new DownSample<Double>(s, period, Double.MAX_VALUE))
                .with((s) -> new Delay<Double>(s, 1, Double.MAX_VALUE))
                .with(Printer.withHeader("Last Period Max Close: "));

        final Indicator<Double> price = new Price().with(Printer.withHeader("Price: "));

        final Indicator<Boolean> isAboveYesterdayClose = new Comparator<Double,Double>(
                price, yesterdayClose,
                (a,b) -> a >= b);

        indicator = new ConditionalTrader(isAboveYesterdayClose, 1, 1);
    }

    @Override
    public Order update(MarketUpdate update) {
        return indicator.update(update);
    }

}
