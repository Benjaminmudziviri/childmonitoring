package com.radical.childmonitoring.devicestats.dto;

public record AppUsageRequest(
        String appName,
        String usageDuration
) {}
