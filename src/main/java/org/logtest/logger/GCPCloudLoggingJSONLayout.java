package org.logtest.logger;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import static ch.qos.logback.classic.Level.*;


public class GCPCloudLoggingJSONLayout extends PatternLayout {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String doLayout(ILoggingEvent event) {
        String formattedMessage = super.doLayout(event);
        return doLayoutInternal(formattedMessage, event);
    }

    private String doLayoutInternal(String formattedMessage, ILoggingEvent event) {
        GCPCloudLoggingEvent gcpLogEvent =
                new GCPCloudLoggingEvent(event.getLoggerName(), formattedMessage, convertToTimeFormat(event.getTimeStamp()),
                        mapLevelToGCPLevel(event.getLevel()), event.getThreadName());

        try {
            return objectMapper.writeValueAsString(gcpLogEvent) + "\n";
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    private static String convertToTimeFormat(long time) {
        Date date = new Date(time);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String formatted = format.format(date);

        return formatted;
    }

    private static int mapLevelToGCPLevel(Level level) {
        switch (level.toInt()) {
            case DEBUG_INT:
                return 100;
            case INFO_INT:
                return 200;
            case WARN_INT:
                return 400;
            case ERROR_INT:
                return 500;
            default:
                return 0;
        }
    }

    public static class GCPCloudLoggingEvent {
        //private String logger;
        private String message;
        //private String time;
        //private String thread;
        private int severity;

        public GCPCloudLoggingEvent(String logger,
                                    String message,
                                    String timestamp,
                                    int severity,
                                    String thread) {
            super();
            //this.logger = logger;
            this.message = message;
            //this.time = timestamp;
            //this.thread = thread;
            this.severity = severity;
        }

        /*
        public String getLogger() {
            return logger;
        }

        public void setLogger(String message) {
            this.logger = logger;
        }
        */

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        /*
        public String getTime() {
            return time;
        }

        public void setTime(String timestamp) {
            this.time = timestamp;
        }


        public String getThread() {
            return thread;
        }

        public void setThread(String thread) {
            this.thread = thread;
        }
        */

        public int getSeverity() {
            return severity;
        }

        public void setSeverity(int severity) {
            this.severity = severity;
        }

    }

    @Override
    public Map<String, String> getDefaultConverterMap() {
        return PatternLayout.defaultConverterMap;
    }
}