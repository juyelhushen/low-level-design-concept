package logger.enums;

public enum LogLevel {

    TRACE(0),
    DEBUG(1),
    INFO(2),
    WARN(3),
    ERROR(4);

    private final int priority;

    LogLevel(int priority) {
        this.priority = priority;
    }

    public boolean isEnabled(LogLevel configuredLevel) {
        return this.priority >= configuredLevel.priority;
    }
}
