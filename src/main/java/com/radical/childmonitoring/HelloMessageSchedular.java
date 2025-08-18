package com.radical.childmonitoring;

import com.radical.childmonitoring.websocket.PanicWebSocketController;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HelloMessageSchedular {

    private final PanicWebSocketController helloWebSocketController;
    

    @Scheduled(fixedRate = 10000) // every 10 seconds
    public void sendHello() {
        helloWebSocketController.sendHelloMessage();
    }
}
