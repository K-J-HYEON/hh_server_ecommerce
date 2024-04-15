package hhplus.ecommerce.storage.orderitem;

import hhplus.ecommerce.config.BaseTimeEntity;
import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.orderitem.OrderItem;
import hhplus.ecommerce.domain.product.Product;
import hhplus.ecommerce.storage.order.OrderEntity;
import hhplus.ecommerce.storage.product.ProductEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OrderItem")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @Column(name = "productName")
    private String productName;

    @Column(name = "unitPrice")
    private Long unitPrice;

    @Column(name = "totalPrice")
    private Long totalPrice;

    @Column(name = "quantity")
    private Long quantity;

    public Long getId() {
        return id;
    }

    public OrderItemEntity(String productName, Long unitPrice, Long totalPrice, Long quantity) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
    }

    public OrderItem toOrderItem() {
        return new OrderItem(
                getId(),
                productName,
                unitPrice,
                totalPrice,
                quantity
        );
    }
}
