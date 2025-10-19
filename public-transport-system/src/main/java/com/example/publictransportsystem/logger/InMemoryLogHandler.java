package com.example.publictransportsystem.logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class InMemoryLogHandler extends Handler {
    private final List<String> logMessages = Collections.synchronizedList(new ArrayList<>());
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void publish(LogRecord record) {
        String formatted = String.format(
                "[%s] %s %s",
                dateFormat.format(new Date(record.getMillis())),
                record.getLevel(),
                record.getMessage()
        );
        logMessages.add(formatted);
    }

    @Override
    public void flush() {}

    @Override
    public void close() throws SecurityException {}

    public List<String> getLogMessages() {
        return new ArrayList<>(logMessages);
    }
}
