package net.narusas.commons.responsecode;

/**
 * 코드가 속할 그룹에 해당하는 정보.
 */
public interface CodeGroupType<T> extends CodeType, CodeEnumSupport<T> {
    /**
     * 비지니스가 복잡할때 각 코드가 일종의 계층구조를 가질 수 있음. 이를 구분하기 위해 계층구조 경로를 기술할수 있게함
     * 실제로는  단순히 메타정보의 역활을 수행함.
     * <p>
     * example) promotion/coupon, promotion/events
     *
     * @return
     */
    String getDirectory();
}

