package logger.strategy.appenders;

import logger.entity.LogEvent;
import logger.strategy.formater.FormatStrategy;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.locks.ReentrantLock;

public final class FileAppenderStrategy implements AppenderStrategy {

    private final Path file;
    private final FormatStrategy formatter;
    private final ReentrantLock lock = new ReentrantLock();

    public FileAppenderStrategy(Path file, FormatStrategy formatter) {
        this.file = file;
        this.formatter = formatter;
    }

    @Override
    public void append(LogEvent event) {
        lock.lock();
        try(BufferedWriter writer = Files.newBufferedWriter(
                file,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
                )) {
            writer.append(formatter.format(event));
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Unable to write log", e);
        } finally {
            lock.unlock();
        }
    }
}
