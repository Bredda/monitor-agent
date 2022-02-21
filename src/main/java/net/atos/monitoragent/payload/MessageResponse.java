package net.atos.monitoragent.payload;

import lombok.Getter;

@Getter
public class MessageResponse {

    private final int code;
    private final String message;

    public MessageResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
