package logger.strategy.formater;

import logger.entity.LogEvent;

public class JsonFormatStrategy implements FormatStrategy {

    public static final JsonFormatStrategy INSTANCE = new JsonFormatStrategy();

    private JsonFormatStrategy() {
    }

    @Override
    public String format(LogEvent event) {
        String result = String.format(
                "{\"timestamp\": \"%s\", " +
                        "\"level\": \"%s\", " +
                        "\"message\": \"%s\"}", event.timestamp(), event.level(), event.message());

        if (event.throwable() != null) {
            result = String.format("%s | exception= %s", result, event.throwable());
        }
        return result;
    }
}
