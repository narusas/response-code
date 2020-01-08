package net.narusas.commons.responsecode;


import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Delegate;

@ToString
public enum CommonBusinessType implements BusinessType {
    COMMON("COMMON", "common", "공통");

    @Getter
    private String code;

    @Getter
    private String directory;

    @Getter
    private String description;


    @Delegate(types = EnumSupport.class)
    EnumSupport delegate;

    private CommonBusinessType(String code, String directory, String description) {
        this.code = code;
        this.directory = directory;
        this.description = description;

        delegate = new BusinessTypeDelegate<CommonBusinessType>(this, CommonBusinessType.class);
    }
}
