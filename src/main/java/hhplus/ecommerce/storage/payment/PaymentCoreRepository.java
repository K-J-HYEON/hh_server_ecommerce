package hhplus.ecommerce.storage.payment;

import hhplus.ecommerce.domain.payment.Payment;
import hhplus.ecommerce.domain.payment.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentCoreRepository implements PaymentRepository {
    private final PaymentJpaRepository paymentJpaRepository;

    public PaymentCoreRepository(PaymentJpaRepository paymentJpaRepository) {
        this.paymentJpaRepository = paymentJpaRepository;
    }

    @Override
    public Payment findById(Long paymentId) {
        return paymentJpaRepository.findById(paymentId)
                .orElseThrow(() -> new EntityNotFoundException("결제 정보를 찾지 못했습니다. - id: " + paymentId))
                .toPayment();
    }

    @Override
    public Payment create(Long payAmount, String paymentMethod) {
        PaymentEntity paymentEntity = new PaymentEntity(payAmount, PayType.of(paymentMethod));
        return paymentJpaRepository.save(paymentEntity).toPayment();
    }
}
