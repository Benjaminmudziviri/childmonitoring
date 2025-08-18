package com.radical.childmonitoring.paniclog.dto;

public record PanicLogRequest(

    Long childId,
    double latitude,
    double longitude
) {
}
