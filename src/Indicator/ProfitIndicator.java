package Indicator;

import Events.Order;
import Events.MarketUpdate.*;

public class ProfitIndicator implements Indicator<Double> {
    private final Indicator<Order> indicator;
    private Order lastAction = new Order(0);
    private Double profit = 0.0;

    public ProfitIndicator(Indicator<Order> indicator) {
        this.indicator = indicator;
    }

    @Override
    public Double update(MarketUpdate update) {
        switch (update) {
            case BarUpdate barUpdate -> updateProfit(barUpdate.bar().close());
            case PriceUpdate priceUpdate -> updateProfit(priceUpdate.price());
        }
        lastAction = indicator.update(update);
        return profit;
    }

    private void updateProfit(Double price) {
        profit -= lastAction.amount() * price;
    }

}
