package hhplus.ecommerce.domain.product;

public record Product (
        Long productId,
        String name,
        Long price,
        Long stockCount,
        String size,
        String color
) {

    public Long orderTotalPrice(Long count) {
        return price * count;
    }

    public boolean isExistProductStock(Long orderCount) {
        return stockCount >= orderCount;
    }

    public Product decreaseStock(Long count) {
        return new Product(productId, name, price, stockCount - count, size, color);
    }
}
