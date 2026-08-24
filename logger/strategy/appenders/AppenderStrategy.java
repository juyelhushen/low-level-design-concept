package logger.strategy.appenders;

import logger.entity.LogEvent;

public interface AppenderStrategy {
    void append(LogEvent event);
}
