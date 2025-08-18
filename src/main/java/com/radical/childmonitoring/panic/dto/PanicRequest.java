package com.radical.childmonitoring.panic.dto;

public record PanicRequest(
        Long childId,
        String message

) {
}
