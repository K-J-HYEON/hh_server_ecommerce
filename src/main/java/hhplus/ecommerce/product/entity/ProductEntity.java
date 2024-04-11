package hhplus.ecommerce.product.entity;

import hhplus.ecommerce.config.BaseTimeEntity;
import hhplus.ecommerce.product.domain.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity extends BaseTimeEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long productId;

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

//    public Long getId() {
//        return productId;
//    }

    public Product toProduct() {
        return new Product(getId(), productName, price, stockCount, size, color);
    }

    public void updateStock(Long stockCount) {
        this.stockCount = stockCount;
    }
}
