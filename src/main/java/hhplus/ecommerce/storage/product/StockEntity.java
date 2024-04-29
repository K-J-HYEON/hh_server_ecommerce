package hhplus.ecommerce.storage.product;

import hhplus.ecommerce.config.BaseTimeEntity;
import hhplus.ecommerce.domain.product.Stock;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ProductStock")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "productId")
    private Long productId;

    @Column(name = "stockCount")
    private Long stockCount;

    public Long getId() {
        return id;
    }

    public Stock toStock() {
        return new Stock(getId(), productId, stockCount);
    }

    public void updateStock(Long stockCount) {
        this.stockCount = stockCount;
    }
}