package hhplus.ecommerce.payment.infrastructure;


import hhplus.ecommerce.payment.domain.Payment;
import hhplus.ecommerce.payment.entity.PaymentEntity;
import hhplus.ecommerce.payment.domain.component.PayType;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentCoreRepository implements PaymentRepository{
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
