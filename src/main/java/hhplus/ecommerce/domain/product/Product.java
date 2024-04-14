package hhplus.ecommerce.domain.product;

public record Product(
        Long id,
        String name,
        Long price,
        Long stockCount,
        String size,
        String color
) {
    public Long orderTotalPrice(Long quantity) {
        return price * quantity;
    }

    public void isEnoughProductStockQuantityForOrder(Long orderQuantity) {
        if (stockCount < orderQuantity) {
            throw new IllegalArgumentException(id + " 상품의 재고가 부족합니다.");
        }
    }

    public Product decreaseStock(Long stockCount) {
        return new Product(id, name, price,this.stockCount - stockCount, size, color);
    }

    public Product increaseStock(Long stockCount) {
        return new Product(id, name, price,this.stockCount + stockCount, size, color);
    }
}
