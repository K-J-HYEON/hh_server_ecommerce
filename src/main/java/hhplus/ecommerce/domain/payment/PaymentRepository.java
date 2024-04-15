package hhplus.ecommerce.domain.payment;

public interface PaymentRepository {
    Payment findById(Long paymentId);

//    Payment create(Long orderId, Long payAmount, String paymentMethod);

    Payment create(Long payAmount, String paymentMethod);
}
