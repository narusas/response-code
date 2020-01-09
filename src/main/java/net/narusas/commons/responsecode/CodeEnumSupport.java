package net.narusas.commons.responsecode;

/**
 * 코드를 가지는 enum들에 대해 검색을 지원하기 위한 인터페이스
 *
 * @param <T>
 */
public interface CodeEnumSupport<T> {
    public boolean contains(String code);

    public T codeOf(String code);
}
