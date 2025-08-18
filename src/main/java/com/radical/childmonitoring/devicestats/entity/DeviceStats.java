package com.radical.childmonitoring.devicestats.entity;

import com.radical.childmonitoring.child.entity.Child;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity representing a snapshot of the child's device statistics.
 * Sent to backend on request, on interval, or under special conditions (e.g., low battery).
 *
 */
//TODO: will be triggered also by a geofence.

@Entity
@Table(name = "device_stats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Device UUID sent by the child app (optional if linked to child entity)
    private String deviceId;

    private String longitude;
    private String latitude;
    private float batteryLevel;

    // Total screen time since last report (format: "2h 30m" or number of minutes)
    private String screenTime;

    // Timestamp of this snapshot
    private LocalDateTime timestamp;

    /**
     * List of apps used — modeled as a separate table (OneToMany) to track per-app info.
     */
    @OneToMany(mappedBy = "deviceStats", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppUsage> appUsages;

    /**
     * Link to the child this report belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "child_id")
    private Child child;
}
