import net.narusas.commons.responsecode.CommonBusinessType;
import net.narusas.commons.responsecode.Sample;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BusinesssTypeTest {
    @Test
    public void test() {
        new Sample();
        assertEquals("COMMON", CommonBusinessType.COMMON.getCode());
        assertEquals(CommonBusinessType.COMMON, CommonBusinessType.COMMON.codeOf("COMMON"));
    }
}
