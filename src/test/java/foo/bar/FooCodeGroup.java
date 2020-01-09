package foo.bar;

import lombok.Getter;
import lombok.experimental.Delegate;
import net.narusas.commons.responsecode.CodeEnumSupport;
import net.narusas.commons.responsecode.CodeGroupDelegate;
import net.narusas.commons.responsecode.CodeGroupType;
import net.narusas.commons.responsecode.common.CommonCodeGroupType;

public enum FooCodeGroup implements CodeGroupType<FooCodeGroup> {
    FOO("FO","foo/bar", "Foo Group");


    @Delegate(types = CodeGroupDelegate.class)
    CodeGroupDelegate delegate;

    private FooCodeGroup(String code, String directory, String description) {
        delegate = new CodeGroupDelegate(this,FooCodeGroup.class, code, directory, description);
    }

}
