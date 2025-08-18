package com.radical.childmonitoring.websocket;

import lombok.Data;

@Data
public class PanicAlertMessage {
    private Long childId;
    private String latitude;
    private String longitude;
    private String message;
}




