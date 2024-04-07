package hhplus.ecommerce.product.entity;

import hhplus.ecommerce.config.BaseTimeEntity;
import hhplus.ecommerce.product.domain.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long productId;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    int price;

    @Column(nullable = false)
    int stock;

    @Column(nullable = false)
    String size;

    @Column(nullable = false)
    String color;

    public Product toProduct() {
        return new Product(getProductId(), name, price, stock, size, color);
    }

    private Long getProductId() {
        return null;
    }
}
