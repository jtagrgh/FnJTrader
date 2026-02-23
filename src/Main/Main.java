package Main;

import Indicator.*;
import Events.MarketUpdate.*;
import Events.Bar;

import System.Tester;

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


        LineBarMaker lineBarMaker = new LineBarMaker();
        Stream<MarketUpdate> marketUpdateStream = lines.stream().map(lineBarMaker::makeBar);

        FixedBreakIndicator breakIndicator = new FixedBreakIndicator(300);
        FixedBreakTrader breakTrader = new FixedBreakTrader(new Printer<FixedBreakIndicator.R>(breakIndicator));
        ClampedTrader clampedTrader = new ClampedTrader(0, 5, breakTrader);
        ProfitIndicator profitIndicator = new ProfitIndicator(clampedTrader);
        UpdateOnce<Double> updateOnce = new UpdateOnce<Double>(profitIndicator);
        Printer<Double> printer = new Printer<Double>(updateOnce);

        Tester tester = new Tester(marketUpdateStream, printer);

        tester.run();
    }

    public static class LineBarMaker {
        private int index = 0;

        public BarUpdate makeBar(String line) {
            final String[] terms = line.split(",");
            final Bar bar = new Bar(Double.valueOf(terms[1]), Double.valueOf(terms[2]),
                    Double.valueOf(terms[3]), Double.valueOf(terms[4]));
            return new BarUpdate(index++, "GOOGL", bar);
        }
    }

}

