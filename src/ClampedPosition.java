public class ClampedPosition implements Indicator<Position> {
    private final Integer upperLimit;
    private final Integer lowerLimit;
    private final Indicator<Order> trader;

    private Integer position = 0;

    public ClampedPosition(Integer upperLimit, Integer lowerLimit, Indicator<Order> trader) {
        this.upperLimit = upperLimit;
        this.lowerLimit = lowerLimit;
        this.trader = trader;
    }

    public ClampedPosition(Indicator<Order> trader) {
        upperLimit = Integer.MAX_VALUE;
        lowerLimit = Integer.MIN_VALUE;
        this.trader = trader;
    }

    @Override
    public void update(MarketUpdate update) {
        trader.update(update);
    }

    @Override
    public Position value() {
        final Order action = trader.value();
        final Integer possiblePosition = position + action.amount();
        if (lowerLimit <= possiblePosition && possiblePosition <= upperLimit) {
            position = possiblePosition;
        }
        return new Position(position);
    }
}
