package hhplus.ecommerce.domain.payment;

import hhplus.ecommerce.api.dto.request.OrderRequest;
import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.order.OrderUpdater;
import hhplus.ecommerce.domain.payment.event.PaymentEventPublisher;
import hhplus.ecommerce.domain.payment.event.PaymentFailEvent;
import hhplus.ecommerce.domain.payment.event.PaymentSuccessEvent;
import hhplus.ecommerce.domain.user.User;
import hhplus.ecommerce.domain.user.UserPointManager;
import hhplus.ecommerce.storage.order.OrderStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

    private final OrderUpdater orderUpdater;
    private final PaymentAppender paymentAppender;
    private final UserPointManager userPointManager;
    private final PaymentEventPublisher paymentEventPublisher;

    private static final String USER_POINT_LOCK_PREFIX = "USER_";

    public PaymentService(OrderUpdater orderUpdater,
                          PaymentAppender paymentAppender,
                          UserPointManager userPointManager, PaymentEventPublisher paymentEventPublisher) {
        this.orderUpdater = orderUpdater;
        this.paymentAppender = paymentAppender;
        this.userPointManager = userPointManager;
        this.paymentEventPublisher = paymentEventPublisher;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public Payment pay(User user, Order order, OrderRequest orderRequest) {
//        userPointManager.usePoint(user, orderRequest.paymentAmount());
//        orderUpdater.changeStatus(order, OrderStatus.PAID);
//        return paymentAppender.create(order, orderRequest.paymentAmount(), orderRequest.paymentMethod());
//    }
    public void pay(Order order) {
        try {
            userPointManager.usePoint(order.userId(), order.payAmount());
            orderUpdater.changeStatus(order, OrderStatus.PAID);
            Payment payment = paymentAppender.create(order, order.payAmount(), order.paymentMethod());
            paymentEventPublisher.success(new PaymentSuccessEvent(order, payment));
        } catch (Exception e) {
            paymentEventPublisher.fail(new PaymentFailEvent(order));
        }
    }
}
