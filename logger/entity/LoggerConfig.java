package logger.entity;

import logger.enums.LogLevel;

import java.util.List;
import java.util.Objects;

public final class LoggerConfig {
    private final LogLevel rootLevel;
    private final List<String> handlerNames;
    private final boolean asyncLogging;

    private LoggerConfig(Builder builder) {
        this.rootLevel = builder.rootLevel;
        this.handlerNames = List.copyOf(builder.handlerNames);
        this.asyncLogging = builder.asyncLogging;
    }

    public LogLevel rootLevel() {
        return rootLevel;
    }

    public List<String> handlerNames() {
        return handlerNames;
    }

    public boolean asyncLogging() {
        return asyncLogging;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private LogLevel rootLevel = LogLevel.INFO;

        private List<String> handlerNames =
                List.of("console");

        private boolean asyncLogging = false;

        public Builder rootLevel(LogLevel rootLevel) {
            this.rootLevel = Objects.requireNonNull(rootLevel);
            return this;
        }

        public Builder handlerNames(List<String> handlerNames) {
            this.handlerNames =
                    List.copyOf(handlerNames);
            return this;
        }

        public Builder asyncLogging(boolean asyncLogging) {
            this.asyncLogging = asyncLogging;
            return this;
        }

        public LoggerConfig build() {
            return new LoggerConfig(this);
        }
    }
}
