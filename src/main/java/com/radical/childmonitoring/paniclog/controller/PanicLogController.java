package com.radical.childmonitoring.paniclog.controller;

import com.radical.childmonitoring.paniclog.dto.PanicLogRequest;
import com.radical.childmonitoring.paniclog.entity.PanicLocationLog;
import com.radical.childmonitoring.paniclog.service.PanicLocationLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/paniclog")
@RequiredArgsConstructor
public class PanicLogController {

    private final PanicLocationLogService locationLogService;


    @PostMapping("/location")
    public void location(@RequestBody PanicLogRequest request){

        locationLogService.saveLocationLog(request);
    }
}
