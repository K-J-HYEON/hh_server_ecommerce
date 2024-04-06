package hhplus.ecommerce.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import static hhplus.ecommerce.config.BaseResponseStatus.SUCCESS;


@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class BaseResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final String message;
    private final int code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;


    // 요청 성공 -> BaseResponse에 정의된 SUCCESS 상수 이용해 result 객체와 함께 반환
    public BaseResponse(T result) {
        this.isSuccess = SUCCESS.isSuccess;
        this.message = SUCCESS.getMessage();
        this.code = SUCCESS.getCode;
        this.result = result;
    }


    // 요청 실패 -> BaseResponseStatus에 정의된 상수 받아와서 반환
    public BaseResponse(BaseResponseStatus status) {
        this.isSuccess = status.isSuccess();
        this.message = status.getMessage();
        this.code = status.getCode;
    }
}
