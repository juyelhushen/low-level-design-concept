package logger.entity;

import logger.enums.LogLevel;

import java.time.Instant;

public record LogEvent(
        Instant timestamp,
        LogLevel level,
        String loggerName,
        String threadName,
        String message,
        Throwable throwable
) {

    // factory method so callers don't manually pass thread/time
    public static LogEvent of(LogLevel level, String message) {
        return new LogEvent(Instant.now(), level, "default", Thread.currentThread().getName(), message, null);
    }
}
