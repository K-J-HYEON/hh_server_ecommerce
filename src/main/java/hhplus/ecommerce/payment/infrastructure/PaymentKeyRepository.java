package hhplus.ecommerce.payment.infrastructure;


import hhplus.ecommerce.payment.domain.Payment;
import hhplus.ecommerce.payment.entity.PaymentEntity;
import hhplus.ecommerce.payment.util.PayType;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentKeyRepository implements PaymentRepository{
    private final PaymentJpaRepository paymentJpaRepository;

    public PaymentKeyRepository(PaymentJpaRepository paymentJpaRepository) {
        this.paymentJpaRepository = paymentJpaRepository;
    }

    @Override
    public Payment create(Long orderId, Long payAmount, String paymentMethod) {
        PaymentEntity paymentEntity = new PaymentEntity(orderId, payAmount, PayType.of(paymentMethod));
        return paymentJpaRepository.save(paymentEntity).toPayment();
    }
}
