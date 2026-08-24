package logger.chainofresponsibility;

import logger.entity.LogEvent;
import logger.enums.LogLevel;
import logger.strategy.appenders.AppenderStrategy;
import logger.strategy.formater.FormatStrategy;

public abstract class LogHandler {
    private final LogLevel level;
    private final AppenderStrategy appenderStrategy;
    private final FormatStrategy formatStrategy;

    private LogHandler next;

    public LogHandler(LogLevel level, AppenderStrategy appenderStrategy, FormatStrategy formatStrategy) {
        this.level = level;
        this.appenderStrategy = appenderStrategy;
        this.formatStrategy = formatStrategy;
    }

    // fluent chain builder: debugHandler.setNext(infoHandler).setNext(warnHandler)
    public LogHandler setNext(LogHandler next) {
        this.next = next;
        return next; // return next so calls can be chained
    }

    /**
     * Core CoR method — final so subclasses can't accidentally break the chain.
     * Every handler always passes to next; only the write step is conditional.
     */
    public final void handle(LogEvent message) {
        // message level must meet or exceed this handler's threshold to write
        if (message.level().ordinal() >= this.level.ordinal()) {
            write(message);
        }
        // always forward — even if this handler didn't write, next one might
        if (next != null) {
            next.handle(message);
        }
    }

    private void write(LogEvent message) {
        String formatted = formatStrategy.format(message);
        appenderStrategy.append( message);
    }

}
