package Indicator;

import Events.MarketUpdate.BarUpdate;
import Events.MarketUpdate.MarketUpdate;
import Events.MarketUpdate.PriceUpdate;
import Events.Order;

public class ConditionalTrader implements Indicator<Order> {

    private final Indicator<Boolean> condition;
    private final Integer buyQuantity;
    private final Integer sellQuantity;

    public ConditionalTrader(Indicator<Boolean> condition, Integer buyQuantity, Integer sellQuantity) {
        this.condition = condition;
        this.buyQuantity = buyQuantity;
        this.sellQuantity = sellQuantity;
    }

    @Override
    public Order update(MarketUpdate update) {
        return condition.update(update) ? new Order(buyQuantity) : new Order(-sellQuantity);
    }

}
