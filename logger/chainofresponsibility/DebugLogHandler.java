package logger.chainofresponsibility;

import logger.enums.LogLevel;
import logger.strategy.appenders.AppenderStrategy;
import logger.strategy.formater.FormatStrategy;

// Concrete handlers are trivial — all logic is in the abstract base.
// Their only job is to declare which level they own.
public class DebugLogHandler extends LogHandler {


    public DebugLogHandler(LogLevel level, AppenderStrategy appenderStrategy, FormatStrategy formatStrategy) {
        super(level, appenderStrategy, formatStrategy);
    }


}
