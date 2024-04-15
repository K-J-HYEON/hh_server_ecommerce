package hhplus.ecommerce.storage.payment;

import hhplus.ecommerce.config.BaseTimeEntity;
import hhplus.ecommerce.domain.payment.Payment;
import hhplus.ecommerce.storage.order.OrderEntity;
import hhplus.ecommerce.storage.product.ProductEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "Payment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

//    @Column(name = "order_id")
//    private Long orderId;

    @Column(name = "payAmount")
    private Long payAmount;

    @Column(name = "paymentMethod")
    @Enumerated(EnumType.STRING)
    private PayType paymentMethod;

    private LocalDateTime createAt;

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createAt;
    }

    public PaymentEntity(Long payAmount, PayType paymentMethod) {
//        this.orderId = orderId;
        this.payAmount = payAmount;
        this.paymentMethod = paymentMethod;
    }

    public Payment toPayment() {
        return new Payment(getId(), payAmount, paymentMethod.toString(), getCreatedAt());
    }
}
