package hhplus.ecommerce.storage.order;

import hhplus.ecommerce.config.BaseTimeEntity;
import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.user.User;


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
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    @Column(name = "user_id")
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

    @Column(name = "orderedAt")
    private LocalDateTime orderedAt;

    public OrderEntity(Long userId,
                       Long payAmount,
                       String receiverName,
                       String address,
                       String phoneNumber,
                       OrderStatus orderStatus,
                       LocalDateTime orderedAt) {
        this.userId = userId;
        this.payAmount = payAmount;
        this.receiverName = receiverName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.orderStatus = orderStatus;
        this.orderedAt = orderedAt;
    }

    public Long getId() {
        return id;
    }

    public Order toOrder() {
        return new Order(getId(), userId, payAmount, receiverName, address, phoneNumber, orderStatus.toString(), orderedAt);
    }

    public void updateStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
