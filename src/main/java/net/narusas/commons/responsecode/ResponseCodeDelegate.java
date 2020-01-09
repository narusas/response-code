package net.narusas.commons.responsecode;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 각 업무 모듈별로 자신만의 ReponseCode를 선언할수 있게 하려면 enum 클래스는 분리되어 있지만, 구현 코드를 공통으로 사용 할수 있어야 한다.
 * lombok의  delegate 기능을 이용해 공통기능을 외부화 시켜서 제공하는 클래스이다.
 * <p>
 * 단일 장소에 모든 ReponseCode를 모아서 사용할때는 불필요한 복잡성으로 작용할수 있다.
 */
public class ResponseCodeDelegate
        <GROUP_TYPE extends CodeGroupType<GROUP_TYPE>, CODE_TYPE extends CodeType>
        extends CodeEnumSupportDelegate<CODE_TYPE>
        implements ResponseCode<GROUP_TYPE> {

    private CodeGroupType groupInstance;

    private GROUP_TYPE group;
    private Enum targetEnum;
    private ResponseType type;
    private String codeNo;
    private String messageCode;
    private String defaultMessage;

    final static Pattern NAME_PATTERN = Pattern.compile("([A-Z]{2})_(\\d{5})_(.*)");


    public ResponseCodeDelegate(GROUP_TYPE groupInstance, Enum targetEnum) {

        super(targetEnum, (Class<? extends CodeType>) targetEnum.getClass());
        this.groupInstance = groupInstance;
        this.targetEnum = targetEnum;
        parse();
    }


    void parse() {

        Matcher m = NAME_PATTERN.matcher(targetEnum.name());
        if (m.find() == false) {
            throw new RuntimeException("ErrorCode  형식에 어긋나는  enum 입니다. :" + targetEnum.name());
        }

        String businessType = m.group(1);
        String codeNo = m.group(2);
        String msg = m.group(3);

        if (businessType.contains(businessType) == false) {
            throw new IllegalStateException("Invalid business type:" + targetEnum.name());
        }

        if (codeNo.startsWith("5")) {
            type = ResponseType.ERROR;
        } else if (codeNo.startsWith("4")) {
            type = ResponseType.FAIL;
        } else if (codeNo.startsWith("0")) {
            type = ResponseType.SUCCESS;
        } else {
            throw new IllegalStateException("Invalid reponse type:" + targetEnum.name());
        }

        this.group = (GROUP_TYPE) groupInstance.codeOf(businessType);
        this.codeNo = codeNo;

        messageCode = businessType + "_" + codeNo;


        defaultMessage = msg.replaceAll("_", " ").replaceAll("X(\\d+)", "{$1}");
        if (defaultMessage == null || "".equals(defaultMessage.trim())) {
            throw new IllegalArgumentException("기본 메시지 가 있어야 합니다. name:" + targetEnum);
        }
    }


    @Override
    public ResponseType getType() {
        return type;
    }

    @Override
    public String getCode() {
        return group.getCode() + "_" + codeNo;
    }

    @Override
    public String getDescription() {
        return defaultMessage;
    }


    public GROUP_TYPE getGroup() {
        return group;
    }

    @Override
    public String getCodeNo() {
        return codeNo;
    }

    @Override
    public String toMessage() {
        return Context.getInstance().getMessageSource().getMessage(getCode(), null, null);
    }

    @Override
    public String toMessage(Object... args) {
        return Context.getInstance().getMessageSource().getMessage(getCode(), args, null);
    }

    @Override
    public String toRawMessage() {
        return toMessage((Object[]) null);
    }

    @Override
    public String toRawMessage(Object... args) {
        return toMessage(args);
    }

    @Override
    public String toUrlEncodedMessage() {
        return defaultMessage;
    }

    @Override
    public String toUrlEncodedMessage(Object... args) {
        return defaultMessage;
    }

    @Override
    public String getDefaultMessage() {
        return defaultMessage;
    }

    @Override
    public void throwException(String overrideMessage) {
        throw createException(overrideMessage, null);
    }

    @Override
    public void throwException() {
        throw createException(toMessage(), null);
    }

    private ResponseException createException(String message, Throwable nested) {
        ResponseException ex = null;
        if (nested == null) {
            ex = new ResponseException(this, message);
        } else {
            ex = new ResponseException(this, message, nested);
        }
        StackTraceElement[] stack = ex.getStackTrace();
        StackTraceElement[] loweredStack = new StackTraceElement[stack.length - 3];
        System.arraycopy(stack, 3, loweredStack, 0, loweredStack.length);
        ex.setStackTrace(loweredStack);
        return ex;
    }

    @Override
    public void throwException(Object... args) {
        throw createException(toMessage(args), null);
    }

    @Override
    public void throwException(Throwable throwable) {
        throw createException(toMessage(), throwable);
    }

    @Override
    public void throwException(Throwable throwable, Object... args) {
        throw createException(toMessage(args), throwable);
    }
}
