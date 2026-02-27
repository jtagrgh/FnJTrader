package Indicator;

import Events.MarketUpdate.MarketUpdate;

public class Comparator<A,B> implements Indicator<Boolean> {

    private final Indicator<A> leftIndicator;
    private final Indicator<B> rightIndicator;
    private final CmpFunc<A,B> cmpFunc;

    public Comparator(Indicator<A> leftIndicator, Indicator<B> rightIndicator, CmpFunc<A, B> cmpFunc) {
        this.leftIndicator = leftIndicator;
        this.rightIndicator = rightIndicator;
        this.cmpFunc = cmpFunc;
    }

    @Override
    public Boolean update(MarketUpdate update) {
        return cmpFunc.cmp(leftIndicator.update(update), rightIndicator.update(update));
    }

}
