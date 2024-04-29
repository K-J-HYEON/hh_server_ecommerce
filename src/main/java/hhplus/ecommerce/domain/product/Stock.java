package hhplus.ecommerce.domain.product;

public record Stock(
        Long id,
        Long productId,
        Long stockCount
) {

    public void isEnoughProductStockCountForOrder(Long orderCount) {
        if (stockCount < orderCount) {
            throw new IllegalArgumentException(id + " 상품의 재고가 부족합니다.");
        }
    }

    public Stock decreaseStock(Long count) {
        return new Stock(id, productId, stockCount - count);
    }

    public Stock increaseStock(Long count) {
        return new Stock(id, productId, stockCount + count);
    }
}
