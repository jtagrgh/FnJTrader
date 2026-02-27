package Main;

import Indicator.*;
import Events.MarketUpdate.*;
import Events.Bar;

import System.Tester;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

class Main {

    public static void main(String[] args) {

        List<String> lines;

        try (Stream<String> lineStream = Files.lines(Paths.get("src/googl_d_1yr.csv"))) {
            lines = lineStream.skip(1).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        LineBarMaker lineBarMaker = new LineBarMaker();
        Stream<PriceUpdate> marketUpdateStream = lines.stream().map(lineBarMaker::makeBar)
                .map(BarUpdate::toPriceUpdate);

        Indicator<Double> indicator = new PrevPeriodHiReversal(10)
                .with(Pyramid.withCap(5))
                .with(ProfitIndicator::new)
                .with(Printer.withHeader("Profit: "));

        Tester tester = new Tester(marketUpdateStream, indicator);

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

