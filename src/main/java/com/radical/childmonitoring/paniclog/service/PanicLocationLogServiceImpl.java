package com.radical.childmonitoring.paniclog.service;

import com.radical.childmonitoring.panic.entity.Panic;
import com.radical.childmonitoring.panic.service.PanicService;
import com.radical.childmonitoring.paniclog.dto.PanicLogRequest;
import com.radical.childmonitoring.paniclog.entity.PanicLocationLog;
import com.radical.childmonitoring.paniclog.repository.PanicLocationLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PanicLocationLogServiceImpl implements PanicLocationLogService {

    private final PanicLocationLogRepository logRepository;
    private final PanicService panicService;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public PanicLocationLog saveLocationLog(PanicLogRequest request) {

        Panic panic = panicService.getPanicByChildId(request.childId());

        PanicLocationLog log = PanicLocationLog.builder()
                .timestamp(LocalDateTime.now())
                .longitude(request.longitude())
                .latitude(request.latitude())
                .panic(panic)
                .build();

        PanicLocationLog saved = logRepository.save(log);

        //Send update to Android via WebSocket
        messagingTemplate.convertAndSend("/topic/panic-logs", saved);

        return saved;
    }



    @Override
    public List<PanicLocationLog> getLogsByPanicId(Long panicId) {
        return logRepository.findByPanicId(panicId);
    }
}
