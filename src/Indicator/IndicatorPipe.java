package Indicator;

@FunctionalInterface
public interface IndicatorPipe<T,R> {
    Indicator<R> pipe(Indicator<T> indicator);
}
