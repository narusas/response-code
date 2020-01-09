package net.narusas.commons.responsecode;

import lombok.Data;

@Data
public class Context {
    private static Context instance = new Context();
    private ResponseCodeMessageSource messageSource = new DefaultMessageSource();

    public static Context getInstance() {
        return instance;
    }

    public static void reset(Context context) {
        instance = context;
    }
}
