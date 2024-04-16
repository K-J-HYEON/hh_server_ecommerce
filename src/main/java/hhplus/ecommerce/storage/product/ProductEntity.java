package hhplus.ecommerce.storage.product;

import hhplus.ecommerce.config.BaseTimeEntity;
import hhplus.ecommerce.domain.product.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "stockCount")
    private Long stockCount;

    @Column(name = "size")
    private String size;

    @Column(name = "color")
    private String color;

    private LocalDateTime createAt;

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createAt;
    }

    public Product toProduct() {
        return new Product(getId(), name, price, stockCount, size, color);
    }

    public void updateStock(Long stockQuantity) {
        this.stockCount = stockQuantity;
    }
}
