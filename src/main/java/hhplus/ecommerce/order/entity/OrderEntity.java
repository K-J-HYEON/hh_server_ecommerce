package hhplus.ecommerce.order.entity;

import hhplus.ecommerce.config.BaseTimeEntity;
import hhplus.ecommerce.order.domain.Order;
import hhplus.ecommerce.order.component.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "Order")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity extends BaseTimeEntity {
    private Long userId;
    private Long payAmount;
    private String receiverName;

    private String address;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime orderAt;


    public OrderEntity(Long userId,
                       Long payAmount,
                       String receiverName,
                       String address,
                       String phoneNumber,
                       OrderStatus orderStatus,
                       LocalDateTime orderAt) {
        this.userId = userId;
        this.payAmount = payAmount;
        this.receiverName = receiverName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.orderStatus = orderStatus;
        this.orderAt = orderAt;
    }

    public Order toOrder() {
        return new Order(getId(), userId, payAmount, receiverName, address, phoneNumber, orderStatus, orderAt);
    }

    public void updateStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
