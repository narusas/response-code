package net.narusas.commons.responsecode.common;


import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Delegate;
import net.narusas.commons.responsecode.CodeEnumSupport;
import net.narusas.commons.responsecode.CodeEnumSupportDelegate;
import net.narusas.commons.responsecode.CodeGroupType;

/**
 * System wide 하게 사용할수 있는 Code Group
 */
@ToString
public enum CommonCodeGroupType implements CodeGroupType<CommonCodeGroupType> {
    COMMON("CM", "common", "공통");


    // CodeGroup 에 대한 구현도 Delegate 처리할수도 있지만... 이정도로 단순한 내용이면 그냥 구현해도 상관없지 않을까 해서 일단 남겨둠

    @Getter
    private String code;

    @Getter
    private String directory;

    @Getter
    private String description;

    static interface InnerGenericSupport extends CodeEnumSupport<CommonCodeGroupType> {
    }

    @Delegate(types = InnerGenericSupport.class)
    CodeEnumSupport<CommonCodeGroupType> delegate;

    private CommonCodeGroupType(String code, String directory, String description) {
        this.code = code;
        this.directory = directory;
        this.description = description;

        delegate = new CodeEnumSupportDelegate<CommonCodeGroupType>(this, CommonCodeGroupType.class);
    }
}
