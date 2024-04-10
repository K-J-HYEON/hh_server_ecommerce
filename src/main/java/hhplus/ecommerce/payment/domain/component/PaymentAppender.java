package hhplus.ecommerce.payment.domain.component;


import hhplus.ecommerce.order.domain.Order;
import hhplus.ecommerce.payment.domain.Payment;
import hhplus.ecommerce.payment.infrastructure.PaymentRepository;
import org.springframework.stereotype.Component;

@Component
public class PaymentAppender {

    private final PaymentRepository paymentRepository;

    public PaymentAppender(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment create(Order order, Long payAmount, String paymentMethod) {
        return paymentRepository.create(order.orderId(), payAmount, paymentMethod);
    }
}
