package logger.strategy.appenders;

import logger.entity.LogEvent;
import logger.strategy.formater.FormatStrategy;

import java.io.PrintStream;

public final class ConsoleAppenderStrategy implements AppenderStrategy {
//    public static final ConsoleAppenderStrategy INSTANCE = new ConsoleAppenderStrategy();

    private final PrintStream output;
    private final FormatStrategy formatStrategy;

    public ConsoleAppenderStrategy(PrintStream output, FormatStrategy formatStrategy) {
        this.output = output;
        this.formatStrategy = formatStrategy;
    }

    @Override
    public void append(LogEvent event) {
        output.println(formatStrategy.format(event));
    }
}
