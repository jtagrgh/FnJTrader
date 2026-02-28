package Utils;

import Indicator.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


import static java.lang.Math.min;

public class LogWriterCSV {

    private final List<Logger<?>> loggers;

    public LogWriterCSV(List<Logger<?>> loggers) {
        this.loggers = loggers;
    }

    public void toCsv(String filePath) {
        final int minSize = loggers.stream().map(Logger::size).reduce(0, Math::min);

        final List<String> header = loggers.stream().map(Logger::header).toList();

        final List<List<String>> rows = IntStream.range(0, minSize).boxed()
                .map((idx) -> loggers.stream().map(logger -> logger.get(idx).toString()).toList())
                .toList();

        try (CSVWriter writer = )
    }
}
