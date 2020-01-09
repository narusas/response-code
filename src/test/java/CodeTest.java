import net.narusas.commons.responsecode.ResponseException;
import net.narusas.commons.responsecode.common.CommonCodeGroupType;
import net.narusas.commons.responsecode.common.CommonResponseCode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CodeTest {
    @Before
    public void setUp() {

    }

    @Test
    public void group() {
        assertEquals("CM", CommonCodeGroupType.COMMON.getCode());
        assertEquals(CommonCodeGroupType.COMMON, CommonCodeGroupType.COMMON.codeOf("CM"));

    }

    @Test
    public void code() {
        assertEquals("CM_00001", CommonResponseCode.CM_00001_성공.getCode());
        assertEquals(CommonCodeGroupType.COMMON, CommonCodeGroupType.COMMON.codeOf("CM"));
        assertEquals("00001", CommonResponseCode.CM_00001_성공.getCodeNo());
        assertEquals("성공", CommonResponseCode.CM_00001_성공.getDescription());
    }

    @Test
    public void message() {
        assertEquals("성공", CommonResponseCode.CM_00001_성공.getDefaultMessage());
    }

    @Test
    public void exception() {

        try {
            CommonResponseCode.CM_50001_오류.throwException(); // 현재 오류가 발생한 라인은 39 라인임
        }catch(ResponseException ex) {
            StackTraceElement[] stacks = ex.getStackTrace();
            assertEquals("내부 스택을 제거 했기 때문에 현재 클래스 CodeTest에서 오류가 발생한것으로 나와야 함", "CodeTest", stacks[0].getClassName());
            assertEquals("내부 스택을 제거 했기 때문에 현재 클래스 CodeTest에서 오류가 발생한것으로 나와야 함", "exception", stacks[0].getMethodName());
            assertEquals("내부 스택을 제거 했기 때문에 현재 클래스 CodeTest에서 오류가 발생한것으로 나와야 함", 39, stacks[0].getLineNumber());
        }
    }
}
