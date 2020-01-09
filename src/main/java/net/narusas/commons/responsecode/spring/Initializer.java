package net.narusas.commons.responsecode.spring;

import net.narusas.commons.responsecode.Context;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.context.MessageSource;

@Component
public class Initializer implements InitializingBean {
    @Autowired
    MessageSource messageSource;

    @Override
    public void afterPropertiesSet() throws Exception {
        Context.getInstance().setMessageSource(new SpringMessageAdapter(messageSource));
    }
}
