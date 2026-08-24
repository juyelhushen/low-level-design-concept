package logger.chainofresponsibility;

import logger.enums.LogLevel;
import logger.strategy.appenders.AppenderStrategy;
import logger.strategy.formater.FormatStrategy;

public class ErrorLogHandler extends LogHandler {

    public ErrorLogHandler(LogLevel level, AppenderStrategy appenderStrategy, FormatStrategy formatStrategy) {
        super(level, appenderStrategy, formatStrategy);
    }
}