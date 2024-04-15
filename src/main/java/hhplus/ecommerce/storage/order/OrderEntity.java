package hhplus.ecommerce.storage.order;

import hhplus.ecommerce.config.BaseTimeEntity;
import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.user.User;
import hhplus.ecommerce.storage.orderitem.OrderItemEntity;
import hhplus.ecommerce.storage.user.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Order")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToMany
    @JoinColumn(name = "orderitem_id")
    private List<OrderItemEntity> orderItemEntities = new ArrayList<>();

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

    public OrderEntity(Long payAmount,
                       String receiverName,
                       String address,
                       String phoneNumber,
                       OrderStatus orderStatus,
                       LocalDateTime orderedAt) {
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
        return new Order(getId(), payAmount, receiverName, address, phoneNumber, orderStatus.toString(), orderedAt);
    }

    public void updateStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
