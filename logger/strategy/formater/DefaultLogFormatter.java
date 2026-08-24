package logger.strategy.formater;

import logger.entity.LogEvent;

public class DefaultLogFormatter  implements FormatStrategy {

    private static final DefaultLogFormatter INSTANCE = new DefaultLogFormatter();
    private DefaultLogFormatter() {}

    @Override
    public String format(LogEvent event) {
        String result =  String.format(" %s [%s] - %s %s - %s",
                event.timestamp(),
                event.threadName(),
                event.level(),
                event.loggerName(),
                event.message()
        );

        if (event.throwable() != null) {
            result = String.format("%s | exception= %s", result, event.throwable());
        }
        return result;
    }
}
