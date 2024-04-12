package hhplus.ecommerce.payment.application;


import hhplus.ecommerce.order.domain.Order;
import hhplus.ecommerce.order.domain.component.OrderReader;
import hhplus.ecommerce.order.domain.component.OrderStatus;
import hhplus.ecommerce.order.domain.component.OrderUpdater;
import hhplus.ecommerce.order.domain.component.OrderValidator;
import hhplus.ecommerce.payment.domain.Payment;
import hhplus.ecommerce.payment.presentation.dto.request.PaymentReq;
import hhplus.ecommerce.payment.domain.component.PaymentAppender;
import hhplus.ecommerce.user.domain.User;
import hhplus.ecommerce.user.infrastructure.UserPointManager;
import hhplus.ecommerce.user.domain.component.UserPointValidator;
import hhplus.ecommerce.user.domain.component.UserReader;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final UserReader userReader;
    private final OrderReader orderReader;
    private final OrderUpdater orderUpdater;
    private final OrderValidator orderValidator;
    private final UserPointManager userPointManager;
    private final UserPointValidator userPointValidator;
    private final PaymentAppender paymentAppender;

    public PaymentServiceImpl(UserReader userReader, OrderReader orderReader, OrderUpdater orderUpdater, OrderValidator orderValidator, UserPointManager userPointManager, UserPointValidator userPointValidator, PaymentAppender paymentAppender) {
        this.userReader = userReader;
        this.orderReader = orderReader;
        this.orderUpdater = orderUpdater;
        this.orderValidator = orderValidator;
        this.userPointManager = userPointManager;
        this.userPointValidator = userPointValidator;
        this.paymentAppender = paymentAppender;
    }

    @Override
    public Payment pay(Long userId, PaymentReq req) {
        User user = userReader.retrieveByUserId(userId);

        Order order = orderReader.retrieveByOrderId(req.orderId());
        orderValidator.confirmOrderStatus(order);

        userPointValidator.confirmUserPoint(order, user, req.payAmount());
        userPointManager.usePoint(user, req.payAmount());

        orderUpdater.changeStatus(order, OrderStatus.PAID);
        return paymentAppender.create(order, req.payAmount(), req.paymentMethod());
    }
}
