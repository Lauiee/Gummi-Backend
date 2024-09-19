package myproject.Gummi.global.exception;

import lombok.Getter;

@Getter
public class ConvertException extends RuntimeException {

    private final ErrorCode errorCode;

    // ErrorCode만을 받아서 해당 예외의 메시지 상위 RuntimeException에 전달
    public ConvertException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    // 추가로 Throwable cause를 받아 원인도 함께 전달
    public ConvertException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }}
