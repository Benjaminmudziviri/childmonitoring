package com.radical.childmonitoring.paniclog.service;

import com.radical.childmonitoring.paniclog.dto.PanicLogRequest;
import com.radical.childmonitoring.paniclog.entity.PanicLocationLog;

import java.util.List;

public interface PanicLocationLogService {
    PanicLocationLog saveLocationLog(PanicLogRequest request);
    List<PanicLocationLog> getLogsByPanicId(Long panicId);
}
