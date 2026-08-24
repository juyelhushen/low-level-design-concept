package logger.strategy.formater;

import logger.entity.LogEvent;

public interface FormatStrategy {
    String format(LogEvent event);
}
