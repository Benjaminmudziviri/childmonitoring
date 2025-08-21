package com.radical.childmonitoring.devicestats.dto;

import java.time.LocalDateTime;
import java.util.List;

public record DeviceStatsRequest(
        String deviceId,
        String longitude,
        String latitude,
        float batteryLevel,
        String screenTime,
        String timestamp,
        Long childId,
        List<AppUsageRequest> apps
) {}
