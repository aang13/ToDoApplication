package com.moinul.ToDoApplication.extension;


import com.moinul.ToDoApplication.common.utils.Time;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;

public class MockTimeExtension implements BeforeEachCallback, AfterEachCallback {
    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        LocalDateTime currentDateTime = LocalDateTime.of(2021, Month.DECEMBER, 6, 12, 45);
        ZoneId zoneId = ZoneId.of("Asia/Dhaka");
        
        Time.useMockTime(currentDateTime, zoneId);
    }
    
    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        Time.useSystemDefaultZoneClock();
    }
}