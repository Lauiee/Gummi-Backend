package myproject.Gummi.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    // 400 BAD REQUEST
    EMPTY_FILE_EXCEPTION(HttpStatus.BAD_REQUEST, "업로드된 파일이 비어 있습니다."),
    NO_FILE_EXTENTION(HttpStatus.BAD_REQUEST, "파일 확장자가 없습니다."),
    INVALID_FILE_EXTENTION(HttpStatus.BAD_REQUEST, "유효하지 않은 파일 확장자입니다." +
            "확장자는 [jpg/jpeg/heic]만 사용가능합니다"),

    // 422 UNPROCESSABLE_ENTITY
    MISSING_SHOOTING_DATE(HttpStatus.UNPROCESSABLE_ENTITY, "촬영시간을 찾을 수 없습니다."),
    FAILED_CONVERT_TO_JPG(HttpStatus.UNPROCESSABLE_ENTITY, "이미지를 JPG타입으로 변환할 수 없습니다."),
    // 500 Internal SERVER ERROR
    IO_EXCEPTION_ON_IMAGE_UPLOAD(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드 중 I/O 오류가 발생했습니다."),
    PUT_OBJECT_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "S3에 객체 업로드 중 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
