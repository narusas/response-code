package net.narusas.commons.responsecode;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResponseException extends RuntimeException {
    @Getter
    private ResponseCode code;

    public ResponseException(ResponseCode code, String message) {
        super(message);
        this.code = code;
    }

    public ResponseException(ResponseCode code, String message, Throwable nested) {
        super(message, nested);
        this.code = code;

    }
}
