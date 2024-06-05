package hhplus.ecommerce.api.error;

public class PaymentMethodException extends RuntimeException {
    public PaymentMethodException(String message) {
        super(message);
    }
}
