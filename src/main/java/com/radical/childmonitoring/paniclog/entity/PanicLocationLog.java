package com.radical.childmonitoring.paniclog.entity;

import com.radical.childmonitoring.panic.entity.Panic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents a single location update sent during an active panic session.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "panic_location_log")
public class PanicLocationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Timestamp of location update
    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    // Many location logs belong to one panic session
    @ManyToOne
    @JoinColumn(name = "panic_id", nullable = false)
    private Panic panic;
}
