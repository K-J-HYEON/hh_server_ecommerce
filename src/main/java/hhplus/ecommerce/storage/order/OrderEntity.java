package hhplus.ecommerce.storage.order;

import hhplus.ecommerce.config.BaseTimeEntity;
import hhplus.ecommerce.domain.order.Order;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "Order")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "payAmount")
    private Long payAmount;

    @Column(name = "receiverName")
    private String receiverName;

    @Column(name = "address")
    private String address;
    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "orderStatus")
    private OrderStatus orderStatus;

    @Column(name = "orderAt")
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
        return new Order(orderId, userId, payAmount, receiverName, address, phoneNumber, orderStatus.toString(), orderAt);
    }

    public void updateStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
