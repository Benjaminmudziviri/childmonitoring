package com.radical.childmonitoring.devicestats.controller;

import com.radical.childmonitoring.devicestats.dto.DeviceStatsRequest;
import com.radical.childmonitoring.devicestats.entity.DeviceStats;
import com.radical.childmonitoring.devicestats.service.DeviceStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/device-stats")
@RequiredArgsConstructor
public class DeviceStatsController {

    private final DeviceStatsService deviceStatsService;

    @PostMapping
    public DeviceStats saveDeviceStats(@RequestBody DeviceStatsRequest request) {
        return deviceStatsService.saveDeviceStats(request);
    }

    @GetMapping("/{childId}")
    public List<DeviceStats> getStatsByChild(@PathVariable Long childId) {
        return deviceStatsService.getStatsByChild(childId);
    }
}
