package net.narusas.commons.responsecode;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CodeEnumSupportDelegate<T extends CodeType> implements CodeEnumSupport<T> {
    private Enum<? extends CodeType> delegateOwner;
    private Class<? extends CodeType> enumClass;
    private Enum<? extends CodeType>[] enumValues;

    private static Map<Class, Enum[]> cache = new ConcurrentHashMap<>();


    public CodeEnumSupportDelegate(Enum<? extends CodeType> owner, Class<? extends CodeType> enumClass) {
        this.delegateOwner = owner;
        this.enumClass = enumClass;
    }

    /**
     * Delegate가 생성되는 시점은 enum instance가  initializing 되는 중이라 enum의 values()를 호출할수 있는 상태가 아니다. enum 클래스에 선언된 enum instance가 순서대로 만들어지는 도중이기 때문에 전체 목록을 파악할수 없는 상태이다.
     * <p>
     * 따라서 class loading initializing이 끝난후에 호출 될 방법이 있으면 그것을 이용해 init를 호출하면 되며, 보통 스프링의 경우 InitializingBean이나 @PostConstruct 를 이용할수 있다.
     * 사용하는 framework에 따라 수정해서 사용하면 된다.
     * <p>
     * 기본 구현에서는 최초 정보 접속하는 외부 요청이 발생할때 init 를 호출하는 방식으로 작업하였다.
     */
    void init() {
        try {
            if (cache.containsKey(enumClass)) {
                enumValues = cache.get(enumClass);
                return;
            }

            Method method = enumClass.getMethod("values", new Class[]{});
            method.setAccessible(true);
            enumValues = (Enum<? extends CodeGroupType>[]) method.invoke(null, new Object[]{});

            cache.put(enumClass, enumValues);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    public boolean contains(String code) {
        if (enumValues == null) {
            init();
        }
        for (Enum<? extends CodeType> enumInstance : enumValues) {
            CodeType type = (CodeType) enumInstance;
            if (type.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T codeOf(String code) {
        if (enumValues == null) {
            init();
        }
        for (Enum<? extends CodeType> enumInstance : enumValues) {
            CodeType type = (CodeType) enumInstance;
            if (type.getCode().equals(code)) {
                return (T) type;
            }
        }

        return null;
    }
}
