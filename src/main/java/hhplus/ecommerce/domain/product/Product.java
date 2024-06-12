package hhplus.ecommerce.domain.product;

import hhplus.ecommerce.storage.product.ProductEntity;

public record Product(
        Long id,
        String name,
        Long price,
        Long stockCount,
        String size,
        String color
) {
    public Long orderTotalPrice(Long orderQuantity) {
        return price * orderQuantity;
    }

    public Product updateStock(Long stockCount) {
        return new Product(id, name, price, stockCount, size, color);
    }

    public Product decreaseStock(Long stockCount) {
        return new Product(id, name, price,this.stockCount - stockCount, size, color);
    }

    public void isEnoughProductStockQuantityForOrder(Long orderQuantity) {
        if (this.stockCount < orderQuantity) {
            throw new IllegalArgumentException(" 상품의 재고가 부족합니다.");
        }
    }
}
