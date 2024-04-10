package hhplus.ecommerce.payment.application;


import hhplus.ecommerce.order.domain.Order;
import hhplus.ecommerce.order.util.OrderRetrieve;
import hhplus.ecommerce.order.util.OrderStatus;
import hhplus.ecommerce.order.util.OrderUpdater;
import hhplus.ecommerce.order.util.OrderValidator;
import hhplus.ecommerce.payment.domain.Payment;
import hhplus.ecommerce.payment.dto.request.PaymentReq;
import hhplus.ecommerce.payment.util.PaymentAppender;
import hhplus.ecommerce.user.domain.User;
import hhplus.ecommerce.user.infrastructure.UserPointManager;
import hhplus.ecommerce.user.util.UserPointValidator;
import hhplus.ecommerce.user.util.UserRetrieve;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final UserRetrieve userRetrieve;
    private final OrderRetrieve orderRetrieve;
    private final OrderUpdater orderUpdater;
    private final OrderValidator orderValidator;
    private final UserPointManager userPointManager;
    private final UserPointValidator userPointValidator;
    private final PaymentAppender paymentAppender;

    public PaymentServiceImpl(UserRetrieve userRetrieve, OrderRetrieve orderRetrieve, OrderUpdater orderUpdater, OrderValidator orderValidator, UserPointManager userPointManager, UserPointValidator userPointValidator, PaymentAppender paymentAppender) {
        this.userRetrieve = userRetrieve;
        this.orderRetrieve = orderRetrieve;
        this.orderUpdater = orderUpdater;
        this.orderValidator = orderValidator;
        this.userPointManager = userPointManager;
        this.userPointValidator = userPointValidator;
        this.paymentAppender = paymentAppender;
    }

    @Override
    public Payment pay(Long userId, PaymentReq req) {
        User user = userRetrieve.retrieveByUserId(userId);

        Order order = orderRetrieve.retrieveByOrderId(req.orderId());
        orderValidator.confirmOrderStatus(order);

        userPointValidator.confirmUserPoint(order, user, req.payAmount());
        userPointManager.usePoint(user, req.payAmount());

        orderUpdater.changeStatus(order, OrderStatus.PAID);
        return paymentAppender.create(order, req.payAmount(), req.paymentMethod());
    }
}
