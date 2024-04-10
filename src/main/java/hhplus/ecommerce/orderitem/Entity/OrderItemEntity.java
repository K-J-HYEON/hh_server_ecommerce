package hhplus.ecommerce.orderitem.Entity;


import hhplus.ecommerce.config.BaseTimeEntity;
import hhplus.ecommerce.orderitem.domain.OrderItem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OrderProductList")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemEntity extends BaseTimeEntity {
    @Column(name = "orderId")
    private Long orderId;

    @Column(name = "productId")
    private Long productId;

    @Column(name = "productName")
    private String productName;

    @Column(name = "count")
    private Long count;

    @Column(name = "price")
    private Long price;

    @Column(name = "totalPrice")
    private Long totalPrice;

    public OrderItemEntity(Long orderId, Long productId, String productName, Long count, Long price, Long totalPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public OrderItem toOrderItem() {
        return new OrderItem(
                getId(),
                orderId,
                productId,
                productName,
                price,
                totalPrice,
                count
        );
    }
}