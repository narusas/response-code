package net.narusas.commons.responsecode;

import lombok.Data;
import lombok.experimental.Delegate;
@Data
public class Sample {

    @Delegate(types = ResponseCode.class)
    ResponseCodeDelegate delegate;
}
