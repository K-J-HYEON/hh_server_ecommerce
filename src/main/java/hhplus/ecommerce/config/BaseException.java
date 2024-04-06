package hhplus.ecommerce.config;


import hhplus.ecommerce.product.dto.res.ProductInfoRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends Exception {
    private BaseResponseStatus status;

    public BaseException(BaseResponseStatus baseResponseStatus) {

    }

    public ProductInfoRes getStatus() {
        return null;
    }
}
