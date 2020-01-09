package foo.bar;

import lombok.experimental.Delegate;
import net.narusas.commons.responsecode.ResponseCode;
import net.narusas.commons.responsecode.ResponseCodeDelegate;
import net.narusas.commons.responsecode.common.CommonCodeGroupType;
import net.narusas.commons.responsecode.common.CommonResponseCode;

public enum FooCodes implements ResponseCode {
    FO_00001_OKOK;

    static interface InnerGenericSupport extends ResponseCode {
    }

    @Delegate(types = ResponseCodeDelegate.class)
    ResponseCodeDelegate delegate;


    private FooCodes() {
        delegate = new ResponseCodeDelegate<FooCodeGroup, FooCodes>(FooCodeGroup.FOO, this);
    }
}
