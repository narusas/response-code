package net.narusas.commons.responsecode;

public interface EnumSupport<T> {
    public boolean contains(String code);

    public T codeOf(String code);
}
