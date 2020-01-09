package net.narusas.commons.responsecode;

import lombok.Getter;

public class CodeGroupDelegate<T extends CodeType> extends CodeEnumSupportDelegate<T> implements CodeGroupType<T> {
    @Getter
    private String code;

    @Getter
    private String directory;

    public String getDirectory() {
        return directory;
    }

    @Getter
    private String description;

    public CodeGroupDelegate(Enum<? extends CodeType> owner, Class<? extends CodeType> enumClass, String code, String directory, String description) {
        super(owner, enumClass);
        this.code = code;
        this.directory = directory;
        this.description = description;
    }

}
