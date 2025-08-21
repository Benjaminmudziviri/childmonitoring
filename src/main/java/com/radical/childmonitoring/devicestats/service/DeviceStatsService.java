package com.radical.childmonitoring.devicestats.service;

import com.radical.childmonitoring.child.entity.Child;
import com.radical.childmonitoring.child.repository.ChildRepository;
import com.radical.childmonitoring.devicestats.dto.AppUsageRequest;
import com.radical.childmonitoring.devicestats.dto.DeviceStatsRequest;
import com.radical.childmonitoring.devicestats.entity.AppUsage;
import com.radical.childmonitoring.devicestats.entity.DeviceStats;
import com.radical.childmonitoring.devicestats.repository.DeviceStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceStatsService {

    private final DeviceStatsRepository deviceStatsRepository;
    private final ChildRepository childRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public DeviceStats saveDeviceStats(DeviceStatsRequest request) {
        Child child = childRepository.findById(request.childId())
                .orElseThrow(() -> new IllegalArgumentException("Child not found"));

        DeviceStats stats = new DeviceStats();
        stats.setDeviceId(request.deviceId());
        stats.setLongitude(request.longitude());
        stats.setLatitude(request.latitude());
        stats.setBatteryLevel(request.batteryLevel());
        stats.setScreenTime(request.screenTime());
        stats.setTimestamp(LocalDateTime.parse(request.timestamp()));
        stats.setChild(child);

        List<AppUsage> appUsages = request.apps().stream()
                .map(app -> {
                    AppUsage usage = new AppUsage();
                    usage.setAppName(app.appName());
                    usage.setUsageDuration(app.usageDuration());
                    usage.setDeviceStats(stats);
                    return usage;
                })
                .collect(Collectors.toList());

        stats.setAppUsages(appUsages);

        DeviceStats saved = deviceStatsRepository.save(stats);

        // Send update to WebSocket subscribers (optional)
        messagingTemplate.convertAndSend("/topic/device-stats", saved);

        return saved;
    }

    public List<DeviceStats> getStatsByChild(Long childId) {
        return deviceStatsRepository.findAll()
                .stream()
                .filter(s -> s.getChild().getId().equals(childId))
                .toList();
    }
}
