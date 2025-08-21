package com.radical.childmonitoring.devicestats.repository;

import com.radical.childmonitoring.devicestats.entity.DeviceStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceStatsRepository extends JpaRepository<DeviceStats, Long> {
}

