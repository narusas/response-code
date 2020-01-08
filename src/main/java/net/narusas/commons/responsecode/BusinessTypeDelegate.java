package net.narusas.commons.responsecode;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BusinessTypeDelegate<T> implements EnumSupport<T> {
    private Enum<? extends BusinessType> targetBusinessTypeEnum;
    private Class<? extends BusinessType> enumClass;
    private Enum<? extends BusinessType>[] businessTypeValues;

    private static Map<Class, Enum[]> cache = new ConcurrentHashMap<>();


    BusinessTypeDelegate(Enum<? extends BusinessType> target, Class<? extends BusinessType> enumClass) {
        this.targetBusinessTypeEnum = target;
        this.enumClass = enumClass;
    }


    public boolean contains(String code) {
        if (businessTypeValues == null) {
            init();
        }
        for (Enum<? extends BusinessType> enumInstance : businessTypeValues) {
            BusinessType type = (BusinessType) enumInstance;
            if (type.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T codeOf(String code) {
        if (businessTypeValues == null) {
            init();
        }
        for (Enum<? extends BusinessType> enumInstance : businessTypeValues) {
            BusinessType type = (BusinessType) enumInstance;
            if (type.getCode().equals(code)) {
                return (T) type;
            }
        }

        return null;
    }


    private void init() {
        try {
            if (cache.containsKey(enumClass)) {
                businessTypeValues = cache.get(enumClass);
                return;
            }
            Method method = enumClass.getMethod("values", new Class[]{});
            method.setAccessible(true);
            businessTypeValues = (Enum<? extends BusinessType>[]) method.invoke(null, new Object[]{});
            cache.put(enumClass, businessTypeValues);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
