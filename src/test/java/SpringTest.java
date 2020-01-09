
import net.narusas.commons.responsecode.Context;
import net.narusas.commons.responsecode.common.CommonResponseCode;
import net.narusas.commons.responsecode.spring.SpringMessageAdapter;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import static org.junit.Assert.assertEquals;

public class SpringTest {

    @Test
    public void springMessage() {
        ReloadableResourceBundleMessageSource msgSource = new ReloadableResourceBundleMessageSource();
        msgSource.setBasename("classpath:springMessage");
        assertEquals("OK", msgSource.getMessage("CM_00001", null, null));
        Context.getInstance().setMessageSource(new SpringMessageAdapter(msgSource));
        assertEquals("OK", CommonResponseCode.CM_00001_성공.toMessage());
    }
}
