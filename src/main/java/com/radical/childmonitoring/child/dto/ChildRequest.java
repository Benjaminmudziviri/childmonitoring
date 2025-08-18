package com.radical.childmonitoring.child.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record ChildRequest(

        String firstName,
        String surName,
        String username,
        String email,
        String password,
        String deviceId



) {
}
