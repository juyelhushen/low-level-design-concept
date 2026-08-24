package logger.entity;

import logger.chainofresponsibility.LogHandler;
import logger.enums.LogLevel;

public final class Logger {

    // Initialization-on-demand holder — same reasoning as every other Singleton:
    // lazy, thread-safe, no synchronized keyword.
    private static class Holder {
        private static final Logger INSTANCE = new Logger();
    }

    public static Logger getInstance() {
        return Holder.INSTANCE;
    }

    // configured minimum level — messages below this are dropped before the chain
    private volatile LogLevel minimumLevel = LogLevel.DEBUG;


    // head of the handler chain
    private LogHandler handlerChain;

    private Logger() {}

    public void setMinimumLevel(LogLevel level) {
        this.minimumLevel = level;
    }

    // called once at startup to wire the chain
    public void setHandlerChain(LogHandler head) {
        this.handlerChain = head;
    }

    // --- public API ---

    public void debug(String message) { log(LogLevel.DEBUG, message); }
    public void info(String message)  { log(LogLevel.INFO,  message); }
    public void warn(String message)  { log(LogLevel.WARN,  message); }
    public void error(String message) { log(LogLevel.ERROR, message); }

    private void log(LogLevel level, String message) {
        // fast-path rejection: don't even build a LogMessage if below minimum level
        if (level.ordinal() < minimumLevel.ordinal()) return;
        if (handlerChain == null) return;

        handlerChain.handle(LogEvent.of(level, message));
    }

}
