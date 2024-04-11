package hhplus.ecommerce.storage.payment;

import hhplus.ecommerce.config.BaseTimeEntity;
import hhplus.ecommerce.domain.payment.Payment;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column(name = "orderId")
    private Long orderId;

    @Column(name = "payAmount")
    private Long payAmount;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PayType paymentMethod;

    public PaymentEntity(Long orderId, Long payAmount, PayType paymentMethod) {
        this.orderId = orderId;
        this.payAmount = payAmount;
        this.paymentMethod = paymentMethod;
    }

    public Payment toPayment() {
        return new Payment(paymentId, orderId, payAmount, paymentMethod.toString(), getCreateAt());
    }
}