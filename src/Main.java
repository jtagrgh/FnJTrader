import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        List<String> lines;

        try (Stream<String> lineStream = Files.lines(Paths.get("src/googl_d_1yr.csv"))) {
            lines = lineStream.skip(1).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stream<MarketUpdate> marketUpdateStream = lines.stream().map(Main::lineToBar);

        FixedBreakIndicator breakIndicator = new FixedBreakIndicator(300);
        FixedBreakTrader breakTrader = new FixedBreakTrader(breakIndicator);
        ClampedTrader clampedTrader = new ClampedTrader(0, 5, breakTrader);
        ProfitIndicator profitIndicator = new ProfitIndicator(clampedTrader);

        marketUpdateStream.map((MarketUpdate e) -> {
            return e.toString() + " -> " + profitIndicator.update(e).toString();
        }).forEach(System.out::println);

    }

    public static BarUpdate lineToBar(String line) {
        final String[] terms = line.split(",");
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssXXX");
        final Instant timestamp = OffsetDateTime.parse(terms[0], formatter).toInstant();
        final Bar bar = new Bar(timestamp, Double.valueOf(terms[1]), Double.valueOf(terms[2]),
                Double.valueOf(terms[3]), Double.valueOf(terms[4]));
        return new BarUpdate(timestamp, "GOOGL", bar);
    }
}

