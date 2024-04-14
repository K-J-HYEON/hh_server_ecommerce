package hhplus.ecommerce.storage.payment;

import hhplus.ecommerce.domain.payment.Payment;
import hhplus.ecommerce.domain.payment.PaymentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentCoreRepository implements PaymentRepository {
    private final PaymentJpaRepository paymentJpaRepository;

    public PaymentCoreRepository(PaymentJpaRepository paymentJpaRepository) {
        this.paymentJpaRepository = paymentJpaRepository;
    }

    @Override
    public Payment create(Long orderId, Long payAmount, String paymentMethod) {
        PaymentEntity paymentEntity = new PaymentEntity(orderId, payAmount, PayType.of(paymentMethod));
        return paymentJpaRepository.save(paymentEntity).toPayment();
    }
}
