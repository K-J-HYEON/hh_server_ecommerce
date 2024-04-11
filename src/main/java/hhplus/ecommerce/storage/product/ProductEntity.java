package hhplus.ecommerce.storage.product;

import hhplus.ecommerce.config.BaseTimeEntity;
import hhplus.ecommerce.domain.product.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long stockCount;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private String color;

    public Product toProduct() {
        return new Product(productId, productName, price, stockCount, size, color);
    }

    private Object getProductId() {
        return null;
    }

    public void updateStock(Long stockCount) {
        this.stockCount = stockCount;
    }
}
