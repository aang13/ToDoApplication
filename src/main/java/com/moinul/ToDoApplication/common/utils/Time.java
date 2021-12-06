package com.moinul.ToDoApplication.common.utils;

import java.time.*;
import java.util.TimeZone;

public class Time {
    private static Clock CLOCK = Clock.systemDefaultZone();
    private static final TimeZone REAL_TIME_ZONE = TimeZone.getDefault();
    
    
    public static LocalDateTime currentDateTime() {
        return LocalDateTime.now(getClock());
    }

    public static void useMockTime(LocalDateTime dateTime, ZoneId testZoneId) {
        Instant instant = dateTime.atZone(testZoneId).toInstant();
        CLOCK = Clock.fixed(instant, testZoneId);
        TimeZone.setDefault(TimeZone.getTimeZone(testZoneId));
    }
    
    public static void useSystemDefaultZoneClock() {
        TimeZone.setDefault(REAL_TIME_ZONE);
        CLOCK = Clock.systemDefaultZone();
    }
    
    private static Clock getClock() {
        return CLOCK;
    }
}