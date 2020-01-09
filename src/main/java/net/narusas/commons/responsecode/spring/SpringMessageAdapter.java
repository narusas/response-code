package net.narusas.commons.responsecode.spring;

import net.narusas.commons.responsecode.ResponseCodeMessageSource;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class SpringMessageAdapter implements ResponseCodeMessageSource {
    private MessageSource messageSource;

    public SpringMessageAdapter(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) {
        return messageSource.getMessage(code, args, locale);
    }


}
