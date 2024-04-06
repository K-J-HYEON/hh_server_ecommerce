package hhplus.ecommerce.config;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    /**
     * 1000 : 요청 성공
     **/
    SUCCESS(true, 1000, "요청에 성공하였습니다,"),


    /**
     * 4000 : Database 오류
     */
    PRODUCT_READ_FAILED(false, 4000, "상품 조회에 실패했습니다.");

    final boolean isSuccess;
    private final int code;
    private final String message;
    public int getCode;

    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return null;
    }

    public Boolean isSuccess() {
        return null;
    }
}
