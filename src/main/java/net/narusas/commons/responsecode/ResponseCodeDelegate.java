package net.narusas.commons.responsecode;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResponseCodeDelegate implements ResponseCode {

    private Class<? extends Enum> businessTypeClass;
    private Enum targetEnum;
    private ResponseType type;
    private String codeNo;
    private String messageCode;
    private String defaultMessage;

    public ResponseCodeDelegate(Class<? extends Enum> businessTypeClass, Enum[] businessTypeValues, Enum targetEnum) {
        this.businessTypeClass = businessTypeClass;
        this.targetEnum = targetEnum;
        parse();
    }

    Pattern namePattern = Pattern.compile("([A-Z]{2})_(\\d{5})_(.*)");


    void parse() {

        Matcher m = namePattern.matcher(targetEnum.name());
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
//
//        this.businessType = businessType.codeOf(businessType);
//        this.codeNo = codeNo;
//
//        messageCode = businessType + "_" + codeNo;
//
//
//        defaultMessage = msg.replaceAll("_", " ").replaceAll("X(\\d+)", "{$1}");
//        Assert.isTrue(StringUtils.isNotEmpty(code.defaultMessage), "기본 메시지 가 있어야 합니다.");
    }


    @Override
    public ResponseType getType() {
        return null;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public BusinessType getBusinessType() {
        return null;
    }

    @Override
    public String getCodeNo() {
        return null;
    }

    @Override
    public String toMessage() {
        return null;
    }

    @Override
    public String toMessage(Object... args) {
        return null;
    }

    @Override
    public String toRawMessage() {
        return null;
    }

    @Override
    public String toRawMessage(Object... args) {
        return null;
    }

    @Override
    public String toUrlEncodedMessage() {
        return null;
    }

    @Override
    public String toUrlEncodedMessage(Object... args) {
        return null;
    }

    @Override
    public String getDefaultMessage() {
        return null;
    }

    @Override
    public void throwException() {

    }

    @Override
    public void throwException(Object... args) {

    }

    @Override
    public void throwException(Throwable throwable) {

    }

    @Override
    public void throwException(Throwable throwable, Object... args) {

    }
}
