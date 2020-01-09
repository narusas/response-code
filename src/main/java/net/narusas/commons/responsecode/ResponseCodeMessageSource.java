package net.narusas.commons.responsecode;


import java.util.Locale;

public interface ResponseCodeMessageSource {
    String getMessage(String code, Object[] args, String defaultMessage, Locale locale);

    String getMessage(String code, Object[] args, Locale locale);
}
