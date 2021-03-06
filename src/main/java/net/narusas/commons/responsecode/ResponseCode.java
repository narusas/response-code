package net.narusas.commons.responsecode;


import java.util.regex.Pattern;

public interface ResponseCode<T> extends CodeType {


    /**
     * SUCCESS, FAIL, ERROR 타입을 반환한다
     *
     * @return
     */
    ResponseType getType();

    /**
     * Business Type + "_" + codeNo 형태의 code를 반환한다
     *
     * @return
     */
    @Override
    String getCode();

    @Override
    String getDescription();

    T getGroup();

    /**
     * 응답 번호를 반환한다. 오류번호가 1로 시작하면 SUCCESS, 4로 시작하면 FAIL, 5로 시작하면 ERROR 인 응답이다
     *
     * @return
     */
    String getCodeNo();

    /**
     * 에러메시지를 반환한다.
     */
    String toMessage();

    /**
     * 주어진 인자를 Format 한 에러 메시지를 반환한다.
     */
    String toMessage(Object... args);


    String toRawMessage();

    String toRawMessage(Object... args);

    /**
     * UTF-8 URL Encoding 된 메시지를 반환한다.
     */
    String toUrlEncodedMessage();

    String toUrlEncodedMessage(Object... args);

    /**
     * MessageSource 에 해당 ErrorCode 에 대한 메시지를 찾을수 없을때 기본으로 사용할 에러 메시지. 평소에는 사용할
     * 필요가 없음
     *
     * @return
     */
    String getDefaultMessage();

    /**
     * 에러코드를 예외로 던진다.
     */
    void throwException();

    void throwException(String overrideMessage);

    /**
     * getMessage(Object... args) 에 넘길 인자를 같이 받는다.
     */
    void throwException(Object... args);

    /**
     * 다른 예외를 감싸서 예외로 발생시킨다.
     */
    void throwException(Throwable throwable);

    /**
     * 다른 예외를 감싸서 예외로 발생시킨다.
     */
    void throwException(Throwable throwable, Object... args);



}
