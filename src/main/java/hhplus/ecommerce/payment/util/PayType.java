package hhplus.ecommerce.payment.util;


import lombok.Getter;

import java.util.Arrays;
import java.util.NoSuchElementException;

@Getter
public enum PayType {
    MOBILE_PAY("MOBILE PAY", 1, "모바일 결제"),
    ACCOUNT_TRANSFER("ACCOUNT_TRANSFER", 2, "계좌 이체"),
    CARD("CARD", 3, "카드 결제");

    private final String method;
    private final int code;
    private final String description;

    PayType(String method, int code, String description) {
        this.method = method;
        this.code = code;
        this.description = description;
    }

    public String toString() {
        return method;
    }

    public static PayType of(String method) {
        return Arrays.stream(PayType.values())
                .filter(payType -> payType.method.equals(method))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("결제 수단이 존재하지 않습니다."));
    }
}
