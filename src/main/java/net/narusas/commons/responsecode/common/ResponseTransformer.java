package net.narusas.commons.responsecode.common;

import net.narusas.commons.responsecode.ResponseCode;

public interface ResponseTransformer<SUCCESS, FAIL, ERROR> {
    SUCCESS success(ResponseCode code, String message);
    SUCCESS success(ResponseCode code, String message, Object data);

    FAIL fail(ResponseCode code, String message);

    ERROR error(ResponseCode code, String message, Object data);
}
