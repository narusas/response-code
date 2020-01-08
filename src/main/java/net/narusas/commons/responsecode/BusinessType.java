package net.narusas.commons.responsecode;

public interface BusinessType<T> extends EnumSupport<T> {
    String getCode();

    String getDirectory();

    String getDescription();
}

