package com.radical.childmonitoring.devicestats.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "app_usage")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String appName;
    private String usageDuration; // e.g., "15m", "2h 5m"

    @ManyToOne
    @JoinColumn(name = "device_stats_id")
    private DeviceStats deviceStats;
}

