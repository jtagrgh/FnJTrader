package Indicator;

import Events.MarketUpdate.MarketUpdate;

public record Printer<R>(Indicator<R> indicator, String header) implements Indicator<R> {

    public Printer(Indicator<R> indicator) {
        this(indicator, "");
    }

    public static <R> IndicatorPipe<R,R> withHeader(String header) {
        return (Indicator<R> s) -> new Printer<R>(s, header);
    }

    @Override
    public R update(MarketUpdate update) {
        final R value = indicator().update(update);
        System.out.println(header + value);
        return value;
    }

}
