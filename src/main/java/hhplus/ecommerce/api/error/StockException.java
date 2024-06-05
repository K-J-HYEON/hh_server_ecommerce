package hhplus.ecommerce.api.error;

public class StockException extends RuntimeException {
    public StockException(String message) {
        super(message);
    }
}
