package com.radical.childmonitoring.panic.entity;

import com.radical.childmonitoring.child.entity.Child;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents a Panic session triggered by a child.
 * Holds the panic metadata such as start time, status, message.
 * Location updates will be stored separately.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "panic")
public class Panic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Panic start timestamp
    @Column(nullable = false)
    private LocalDateTime panicStart;

    // Panic end timestamp (nullable until panic ends)
    private LocalDateTime panicEnd;

    // Status of panic: active or inactive
    @Column(nullable = false)
    private boolean active;

    // Optional panic message set at start
    private String message;

    // Link the panic session to the child who triggered it
    @ManyToOne
    @JoinColumn(name = "child_id", nullable = false)
    private Child child;
}
