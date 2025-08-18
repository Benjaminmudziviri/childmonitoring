package com.radical.childmonitoring.websocket;


import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class PanicWebSocketController {
    private final SimpMessagingTemplate messagingTemplate;

    public PanicWebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Use this method to send panic alert to parent devices
    public void sendPanicAlert(PanicAlertMessage alert) {
        messagingTemplate.convertAndSend("/topic/panic-alerts", alert);
    }

    public void sendHelloMessage() {
        HelloMessage message = new HelloMessage("Hello from backend!");
        messagingTemplate.convertAndSend("/topic/hello", message);
    }
}



