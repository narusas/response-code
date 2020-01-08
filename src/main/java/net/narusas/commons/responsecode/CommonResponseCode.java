package net.narusas.commons.responsecode;

import lombok.experimental.Delegate;


/**
 * System Wide 하게 사용할수 있는 공통 응답코드를 정의함
 */
public enum CommonResponseCode implements ResponseCode {
    COMMON_001;

    @Delegate(types = ResponseCode.class)
    ResponseCodeDelegate delegate;

    private CommonResponseCode() {
        delegate = new ResponseCodeDelegate(CommonBusinessType.class, CommonBusinessType.values(), this);
    }
}
