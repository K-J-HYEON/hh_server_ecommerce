package hhplus.ecommerce.payment.infrastructure;

import hhplus.ecommerce.payment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, Long> {
}
