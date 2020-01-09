package net.narusas.commons.responsecode.common;

import lombok.experimental.Delegate;
import net.narusas.commons.responsecode.CodeEnumSupport;
import net.narusas.commons.responsecode.ResponseCode;
import net.narusas.commons.responsecode.ResponseCodeDelegate;


/**
 * System Wide 하게 사용할수 있는 공통 응답코드를 정의함
 */
public enum CommonResponseCode implements ResponseCode {

    CM_00001_성공,
    CM_40001_실패,
    CM_50001_오류,
    ;



    static interface InnerGenericSupport extends ResponseCode {
    }

    @Delegate(types = InnerGenericSupport.class)
    ResponseCodeDelegate delegate;


    private CommonResponseCode() {
        delegate = new ResponseCodeDelegate<CommonCodeGroupType, CommonResponseCode>(CommonCodeGroupType.COMMON, this);
    }
}
