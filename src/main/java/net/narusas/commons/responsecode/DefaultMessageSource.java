package net.narusas.commons.responsecode;

import java.util.Locale;

public class DefaultMessageSource implements ResponseCodeMessageSource {
    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return defaultMessage == null ? code : defaultMessage;
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) {
        return code;
    }
}
